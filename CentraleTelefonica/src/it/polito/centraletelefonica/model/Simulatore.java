package it.polito.centraletelefonica.model;

import java.time.LocalTime;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.traverse.ClosestFirstIterator;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Simulatore {

	private static final LocalTime INIZIO_SIMULAZIONE = LocalTime.of(8, 00);
	private LocalTime currentTime;
	private static final LocalTime FINE_SIMULAZIONE = LocalTime.of(18, 00);
	private PriorityQueue<Evento> eventi;
	private MioGrafo grafo;

	// STATISTICHE

	private int operazioniConcluse;

	public Simulatore(MioGrafo grafo) {
		this.eventi = new PriorityQueue<>();
		currentTime = INIZIO_SIMULAZIONE;
		this.grafo = grafo;
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
		while (!eventi.isEmpty() && currentTime.compareTo(FINE_SIMULAZIONE) < 0) {

			// scagliono il tempo in minuti
			currentTime = currentTime.plusMinutes(1);

			Evento ev = eventi.poll();
			Operatore op = ev.getOperatore();

			System.out.println(op.getStato());

			switch (op.getStato()) {
			// se un operatore è occupato verifico se ha completato l'operazione
			case "occupato":
				verifica(op, ev);
				break;

			case "in viaggio":
				// se un operatore è in viaggio devo controllare che sia ancora necessario che
				// si muova verso la destinazione, se non più necessario devo fargli cambiare
				// rotta
				continua(op, ev);
				break;

			case "libero":
				// assegno all'operatore una nuova destinazione
				assegna(op, ev);

			default:
				break;
			}

		}

		// alla fine stampo statistiche
		System.out.println(operazioniConcluse);

	}

	private void assegna(Operatore op, Evento ev) {

		ClosestFirstIterator<Nodo, DefaultWeightedEdge> closest = new ClosestFirstIterator<Nodo, DefaultWeightedEdge>(
				grafo, op.getOperazioneAttuale());

		// il primo giro a vuoto perché corrisponde alla partenza

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
		Evento evento = new Evento(op, currentTime, currentTime.plusSeconds((long) secondi));

	}

	private void continua(Operatore op, Evento ev) {

		// se sul posto ci sono già gli operatori richiesti l'operatore deve cambiare
		// rotta

		Operation nextOp = op.getOperationTarget();

		if (nextOp.getStato() == "IN_CORSO") {
			System.out.println("Op in corso");
			ClosestFirstIterator<Nodo, DefaultWeightedEdge> closest = new ClosestFirstIterator<>(grafo, nextOp);
			// salto il primo nodo perché corrisponde alla partenza
			closest.next();
			Operation nextDestination = (Operation) closest.next();
			// nonostante non sia effettivamente arrivato sul posto suppongo che invece lo
			// sia per facilitare i calcoli del percorso da un punto ad un altro non potendo
			// conoscere la posizione esatta di un punto intermedio tra un'operazione ed
			// un'altra
			DefaultWeightedEdge edge = grafo.getEdge(nextOp, nextDestination);
			double tempo = grafo.getEdgeWeight(edge) / nextDestination.getOperatoriRichiesti();
			Evento evento = new Evento(op, currentTime, currentTime.plusSeconds((long) tempo));
			eventi.add(evento);
		}

		// se invece l'operazione non ha raggiunto il numero di operatori e l'operatore
		// arriva sul posto allora l'operazione assume uno stato in corso e l'operatore
		// diventa occupato

		if (nextOp.getStato() != "IN_CORSO") {

			double distance = 0;

			// inizialmente il punto di partenza è una centrale

			if (op.getCenter() != null) {

				distance = LatLngTool.distance(
						new com.javadocmd.simplelatlng.LatLng(op.getCenter().getLatLng().lat,
								op.getCenter().getLatLng().lng),
						new com.javadocmd.simplelatlng.LatLng(op.getOperationTarget().getCoordinate().lat,
								op.getOperationTarget().getCoordinate().lng),
						LengthUnit.METER);

			} else {

				distance = LatLngTool.distance(
						new com.javadocmd.simplelatlng.LatLng(op.getOperazioneAttuale().getCoordinate().lat,
								op.getOperazioneAttuale().getCoordinate().lng),
						new com.javadocmd.simplelatlng.LatLng(op.getOperationTarget().getCoordinate().lat,
								op.getOperationTarget().getCoordinate().lng),
						LengthUnit.METER);

			}

			// Supponiamo una velocita' di 14 m/s, un po' piu' di 50 km/h.
			double secondi = distance / 14;

			if (currentTime.compareTo(ev.getInitTime().plusSeconds((long) secondi)) >= 0) {
				nextOp.addRichiedente(op);
				// tempo per il quale l'operatore sarà occupato
				double secRichiesti = op.getOperationTarget().getMedia() * 60;
				op.setStato("occupato");
				Evento eve = new Evento(op, currentTime, currentTime.plusSeconds((long) secRichiesti));
				eventi.add(eve);
			}

		}

	}

	private void verifica(Operatore op, Evento ev) {

		// operazione terminata
		if (currentTime.compareTo(ev.getTargetTime()) >= 0) {
			// elimino il nodo
			grafo.removeVertex(op.getOperazioneAttuale());
			// l'operazione target diventa la posizione attuale
			op.setOperazioneAttuale(op.getOperationTarget());
			// l'operatore diventa libero
			op.setStato("libero");
			Evento evento = new Evento(op, currentTime, currentTime);
			operazioniConcluse++;
			eventi.add(evento);
		}

	}

}
