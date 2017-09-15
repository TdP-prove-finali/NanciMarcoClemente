package it.polito.centraletelefonica.model;

public class OperationType {
	
	private String description;
	private double media;
	private double varianza;
	private int neededWorkers;

	public OperationType(String description, double media, double varianza, int neededWorkers) {
		this.setDescription(description);
		this.setMedia(media);
		this.setVarianza(varianza);
		this.setNeededWorkers(neededWorkers);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getVarianza() {
		return varianza;
	}

	public void setVarianza(double varianza) {
		this.varianza = varianza;
	}

	public int getNeededWorkers() {
		return neededWorkers;
	}

	public void setNeededWorkers(int neededWorkers) {
		this.neededWorkers = neededWorkers;
	}

}
