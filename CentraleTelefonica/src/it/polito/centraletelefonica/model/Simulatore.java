package it.polito.centraletelefonica.model;

import java.time.LocalTime;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.traverse.ClosestFirstIterator;

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

			default:
				break;
			}

		}

		// alla fine stampo statistiche
		System.out.println(operazioniConcluse);

	}

	private void continua(Operatore op, Evento ev) {

		System.out.println("entro in continua");

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

	}

	private void verifica(Operatore op, Evento ev) {

		System.out.println("entro in verifica");

		// operazione terminata
		if (currentTime.compareTo(ev.getTargetTime()) >= 0) {
			// cerco la prossima destinazione
			ClosestFirstIterator<Nodo, DefaultWeightedEdge> closest = new ClosestFirstIterator<>(grafo,
					op.getOperazioneAttuale());
			// salto il primo nodo perché corrisponde alla partenza
			closest.next();
			// prossima operazione
			Operation nextOp = (Operation) closest.next();
			nextOp.addRichiedente(op);
			op.setStato("in viaggio");
			op.setOperationTarget(nextOp);
			DefaultWeightedEdge edge = grafo.getEdge(op.getOperazioneAttuale(), nextOp);
			double tempo = grafo.getEdgeWeight(edge) / nextOp.getOperatoriRichiesti();
			Evento evento = new Evento(op, currentTime, currentTime.plusSeconds((long) tempo));
			grafo.removeVertex(nextOp);
			operazioniConcluse++;
			eventi.add(evento);
		}

	}

}
