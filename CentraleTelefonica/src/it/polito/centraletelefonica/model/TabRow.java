package it.polito.centraletelefonica.model;

/**
 * Super Bean per le righe delle tabs
 * 
 * @author GJCode
 *
 */

public class TabRow {

	private String periodo;
	private double mediaPeriodo;
	private double diffPunti;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public double getMediaPeriodo() {
		return mediaPeriodo;
	}

	public void setMediaMese(double mediaPeriodo) {
		this.mediaPeriodo = mediaPeriodo;
	}

	public double getDiffPunti() {
		return diffPunti;
	}

	public void setDiffPunti(double diffPunti) {
		this.diffPunti = diffPunti;
	}

}
