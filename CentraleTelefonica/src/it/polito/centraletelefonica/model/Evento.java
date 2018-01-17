package it.polito.centraletelefonica.model;

/**
 * Superclasse evento che descrive gli eventi che fanno riferimento agli
 * operatori
 * 
 * @author GJCode
 *
 */
public class Evento {

	private Operatore operatore;
	private int initTime;
	private int targetTime;

	public Evento(Operatore operatore, int initTime, int targetTime) {
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

	public int getInitTime() {
		return initTime;
	}

	public void setInitTime(int initTime) {
		this.initTime = initTime;
	}

	public int getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(int targetTime) {
		this.targetTime = targetTime;
	}

	@Override
	public String toString() {
		return String.join(" ", operatore.toString(), String.valueOf(targetTime));
	}

}
