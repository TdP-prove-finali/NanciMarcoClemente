package it.polito.centraletelefonica.model;

import com.google.maps.model.LatLng;

public class OperationCenter {

	private String operationCenterID;
	private String operationCenterName;
	private String operationCenterStreet;
	private LatLng cordinates;

	public OperationCenter(String operationCenterID, String operationCenterName, String operationCenterStreet,
			LatLng cordinates) {

		this.setOperationCenterID(operationCenterID);
		this.setOperationCenterName(operationCenterName);
		this.setOperationCenterStreet(operationCenterStreet);
		this.setCordinates(cordinates);
	}

	public String getOperationCenterID() {
		return operationCenterID;
	}

	public void setOperationCenterID(String operationCenterID) {
		this.operationCenterID = operationCenterID;
	}

	public String getOperationCenterName() {
		return operationCenterName;
	}

	public void setOperationCenterName(String operationCenterName) {
		this.operationCenterName = operationCenterName;
	}

	public String getOperationCenterStreet() {
		return operationCenterStreet;
	}

	public void setOperationCenterStreet(String operationCenterStreet) {
		this.operationCenterStreet = operationCenterStreet;
	}

	public LatLng getCordinates() {
		return cordinates;
	}

	public void setCordinates(LatLng cordinates) {
		this.cordinates = cordinates;
	}

	@Override
	public String toString() {
		return String.join(" ", operationCenterID, operationCenterName, operationCenterStreet);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operationCenterID == null) ? 0 : operationCenterID.hashCode());
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
		OperationCenter other = (OperationCenter) obj;
		if (operationCenterID == null) {
			if (other.operationCenterID != null)
				return false;
		} else if (!operationCenterID.equals(other.operationCenterID))
			return false;
		return true;
	}
	
	

}
