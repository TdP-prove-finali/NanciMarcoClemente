package it.polito.centraletelefonica.model;

import java.time.LocalDate;

import com.google.maps.model.LatLng;

public class Operation {

	private int operationID;
	private LocalDate reportingDate;
	private String urgengy, operationType, priority, city, street;
	private Worker operator;
	private OperationCenter operationCenter;
	private LatLng cordinates;

	public Operation(int operationID, LocalDate reportingDate, String urgengy) {
		this.setOperationID(operationID);
		this.setReportingDate(reportingDate);
		this.setUrgengy(urgengy);
	}

	public int getOperationID() {
		return operationID;
	}

	public void setOperationID(int operationID) {
		this.operationID = operationID;
	}

	public LocalDate getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(LocalDate reportingDate) {
		this.reportingDate = reportingDate;
	}

	public String getUrgengy() {
		return urgengy;
	}

	public void setUrgengy(String urgengy) {
		this.urgengy = urgengy;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
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

	@Override
	public String toString() {
		return String.join(" ", String.valueOf(operationID), reportingDate.toString(), urgengy);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operationID;
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
		if (operationID != other.operationID)
			return false;
		return true;
	}

	public LatLng getCordinates() {
		return cordinates;
	}

	public void setCordinates(LatLng cordinates) {
		this.cordinates = cordinates;
	}
	
	

}
