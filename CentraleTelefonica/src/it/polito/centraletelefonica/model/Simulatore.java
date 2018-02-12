package it.polito.centraletelefonica.model;

import java.time.LocalTime;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.traverse.ClosestFirstIterator;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Simulatore {

	private static final LocalTime INIZIO_SIMULAZIONE = LocalTime.of(8, 00, 00);
	private LocalTime currentTime;
	private static final LocalTime FINE_SIMULAZIONE = LocalTime.of(18, 00, 00);
	private PriorityQueue<Evento> eventi;
	private MioGrafo grafo;

	// STATISTICHE

	private int operazioniConcluse;
	private String esitoSimulazione;

	public Simulatore(MioGrafo grafo) {
		this.eventi = new PriorityQueue<>();
		currentTime = INIZIO_SIMULAZIONE;
		this.grafo = grafo;
		esitoSimulazione = new String();
	}

	public void inizializzaCoda(List<Evento> initEventi) {
		eventi.addAll(initEventi);
	}

	/**
	 * si suppone che alle 8, inizio della giornata gli operatori si trovino sul
	 * posto della prima operazione da svolgere in giornata
	 */

	public void run() {

		// clausola d'uscita: operazioni concluse o fine giornata.
		while (currentTime.compareTo(FINE_SIMULAZIONE) < 0) {

			// scagliono il tempo in secondi
			currentTime = currentTime.plusSeconds(1);

			// in alcuni momenti la coda potrebbe essere vuota
			// if (!eventi.isEmpty()) {

			Evento ev = eventi.poll();
			Operatore op = ev.getOperatore();

			System.out.println("evento: " + ev.toString() + " timestamp simulazione: " + currentTime);

			switch (op.getStato()) {
			// se un operatore � occupato verifico se ha completato l'operazione
			case "occupato":
				verifica(op, ev);
				break;

			case "in viaggio":
				// se un operatore � in viaggio devo controllare che sia ancora necessario che
				// si muova verso la destinazione, se non pi� necessario devo fargli cambiare
				// rotta
				continua(op, ev);
				break;

			case "libero":
				// assegno all'operatore una nuova destinazione
				assegna(op, ev);

			default:
				break;
			}

			// }

		}

		// alla fine stampo statistiche
		System.out.println(esitoSimulazione);
		System.out.println(operazioniConcluse);

	}

	private void assegna(Operatore op, Evento ev) {

		ClosestFirstIterator<Nodo, DefaultWeightedEdge> closest = new ClosestFirstIterator<Nodo, DefaultWeightedEdge>(
				grafo, op.getOperazioneAttuale());

		// il primo giro a vuoto perch� corrisponde alla partenza

		closest.next();
		Operation nextOp = (Operation) closest.next();

		double distance = LatLngTool.distance(
				new com.javadocmd.simplelatlng.LatLng(op.getOperazioneAttuale().getCoordinate().lat,
						op.getOperazioneAttuale().getCoordinate().lng),
				new com.javadocmd.simplelatlng.LatLng(op.getOperationTarget().getCoordinate().lat,
						op.getOperationTarget().getCoordinate().lng),
				LengthUnit.METER);

		// Supponiamo una velocita' di 14 m/s, un po' piu' di 50 km/h.
		double secondi = distance / 14;
		op.setStato("in viaggio");
		op.setOperationTarget(nextOp);
		Evento evento = new Evento(op, currentTime, currentTime.plusSeconds((long) secondi));
		eventi.add(evento);
		if (evento.toString().contains("Operatore 1 "))
			esitoSimulazione += evento.toString() + "\n";

	}

	private void continua(Operatore op, Evento ev) {

		// se sul posto ci sono gi� gli operatori richiesti l'operatore deve cambiare
		// rotta

		Operation nextOp = op.getOperationTarget();

		if (nextOp.getStato() == "IN_CORSO") {
			ClosestFirstIterator<Nodo, DefaultWeightedEdge> closest = new ClosestFirstIterator<>(grafo, nextOp);
			// salto il primo nodo perch� corrisponde alla partenza
			closest.next();
			Operation nextDestination = (Operation) closest.next();
			// nonostante non sia effettivamente arrivato sul posto suppongo che invece lo
			// sia per facilitare i calcoli del percorso da un punto ad un altro non potendo
			// conoscere la posizione esatta di un punto intermedio tra un'operazione ed
			// un'altra
			DefaultWeightedEdge edge = grafo.getEdge(nextOp, nextDestination);
			op.setOperationTarget(nextDestination);
			double tempo = (grafo.getEdgeWeight(edge) / nextDestination.getOperatoriRichiesti())
					- nextDestination.getMedia() * 60;
			op.setStato("in viaggio");
			Evento evento = new Evento(op, currentTime, currentTime.plusSeconds((long) tempo));
			eventi.add(evento);
			if (evento.toString().contains("Operatore 1 "))
				esitoSimulazione += evento.toString() + "\n";
		}

		// se invece l'operazione non ha raggiunto il numero di operatori e l'operatore
		// arriva sul posto allora l'operazione assume uno stato in corso e l'operatore
		// diventa occupato

		if (nextOp.getStato() != "IN_CORSO") {

			// se l'operatore arriva sul posto si occupa dell'operazione
			if (currentTime.compareTo(ev.getTargetTime()) >= 0) {
				nextOp.addRichiedente(op);
				// tempo per il quale l'operatore sar� occupato
				double secRichiesti = op.getOperationTarget().getMedia() * 60;
				op.setStato("occupato");
				op.setOperazioneAttuale(nextOp);
				Evento eve = new Evento(op, currentTime, currentTime.plusSeconds((long) secRichiesti));
				eventi.add(eve);
				if (eve.toString().contains("Operatore 1 "))
					esitoSimulazione += eve.toString() + "\n";
			}
			// altrimenti re-inserisco l'operazione in coda perch� non trattata
			eventi.add(ev);

		}

	}

	private void verifica(Operatore op, Evento ev) {

		// operazione terminata
		if (currentTime.compareTo(ev.getTargetTime()) >= 0) {
			// elimino il nodo
			grafo.removeVertex(op.getOperazioneAttuale());
			// l'operazione target diventa la posizione attuale
			op.setOperazioneAttuale(op.getOperationTarget());
			// gli operatori diventano liberi
			op.getOperationTarget().liberaOperatori();
			op.setStato("libero");
			Evento evento = new Evento(op, currentTime, currentTime);
			operazioniConcluse++;
			eventi.add(evento);
			if (evento.toString().contains("Operatore 1 "))
				esitoSimulazione += evento.toString() + "\n";
		}
		// altrimenti aggiungo di nuovo l'evento non trattato
		else
			eventi.add(ev);

	}

}
