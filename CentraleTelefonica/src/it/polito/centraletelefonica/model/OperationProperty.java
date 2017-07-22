package it.polito.centraletelefonica.model;

import javafx.beans.property.SimpleStringProperty;

public class OperationProperty {

	private SimpleStringProperty id, localDate, urgency;

	public OperationProperty(Operation operation) {
		setId(new SimpleStringProperty("" + operation.getID()));
		setLocalDate(new SimpleStringProperty(operation.getReportingDate().toString()));
		setUrgency(new SimpleStringProperty(operation.getUrgency()));
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public SimpleStringProperty getLocalDate() {
		return localDate;
	}

	public void setLocalDate(SimpleStringProperty localDate) {
		this.localDate = localDate;
	}

	public SimpleStringProperty getUrgency() {
		return urgency;
	}

	public void setUrgency(SimpleStringProperty urgency) {
		this.urgency = urgency;
	}

}
