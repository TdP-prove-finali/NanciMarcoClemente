package it.polito.centraletelefonica.model;

import java.time.LocalDate;

import com.google.maps.model.LatLng;

public class Operation {

	private String id;
	private LocalDate reportingDate, goalDate;
	private String urgency, operationType, priority, city, street;
	private Worker operator;
	private OperationCenter operationCenter;
	private LatLng cordinates;

	public Operation(String operationID, LocalDate reportingDate, String urgency) {
		this.setID(operationID);
		this.setReportingDate(reportingDate);
		this.setUrgency(urgency);
	}

	public String getID() {
		return id;
	}

	public void setID(String operationID) {
		this.id = operationID;
	}

	public LocalDate getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(LocalDate reportingDate) {
		this.reportingDate = reportingDate;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgengy) {
		this.urgency = urgengy;
	}

	public String getType() {
		return operationType;
	}

	public void setType(String operationType) {
		this.operationType = operationType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Worker getOperator() {
		return operator;
	}

	public void setOperator(Worker operator) {
		this.operator = operator;
	}

	public OperationCenter getOperationCenter() {
		return operationCenter;
	}

	public void setOperationCenter(OperationCenter operationCenter) {
		this.operationCenter = operationCenter;
	}

	public LatLng getCordinates() {
		return cordinates;
	}

	public void setCordinates(LatLng cordinates) {
		this.cordinates = cordinates;
	}

	public LocalDate getGoalDate() {
		return goalDate;
	}

	public void setGoalDate(LocalDate goalDate) {
		this.goalDate = goalDate;
	}

	@Override
	public String toString() {
		return String.join(" ", String.valueOf(id), reportingDate.toString(), urgency);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
