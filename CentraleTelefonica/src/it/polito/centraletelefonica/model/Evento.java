package it.polito.centraletelefonica.model;

import java.time.LocalTime;

/**
 * Superclasse evento che descrive gli eventi che fanno riferimento agli
 * operatori
 * 
 * @author GJCode
 *
 */
public class Evento implements Comparable<Evento> {

	private Operatore operatore;
	private LocalTime initTime;
	private LocalTime targetTime;

	public Evento(Operatore operatore, LocalTime initTime, LocalTime targetTime) {
		this.setOperatore(operatore);
		this.setInitTime(initTime);
		this.setTargetTime(targetTime);
	}

	public Operatore getOperatore() {
		return operatore;
	}

	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}

	public LocalTime getInitTime() {
		return initTime;
	}

	public void setInitTime(LocalTime initTime) {
		this.initTime = initTime;
	}

	public LocalTime getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(LocalTime targetTime) {
		this.targetTime = targetTime;
	}

	@Override
	public String toString() {
		String toString = String.join(" ", operatore.toString(), String.valueOf(initTime));
		if (operatore.getStato() == "in viaggio")
			toString += " in viaggio verso " + operatore.getOperationTarget().getIndirizzo();
		if (operatore.getStato() == "occupato")
			toString += " operatore occupato presso " + operatore.getOperationTarget().getIndirizzo();
		if (operatore.getStato() == "libero")
			toString += " operatore conclude operazione in " + operatore.getOperazioneAttuale().getIndirizzo();
		return toString;
	}

	@Override
	public int compareTo(Evento arg0) {
		return this.initTime.compareTo(arg0.getInitTime());
	}

}
