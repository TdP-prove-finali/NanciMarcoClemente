package it.polito.centraletelefonica.model;

import java.time.LocalDate;

import com.google.maps.model.LatLng;

public class Operation {

	private String id;
	private OperationTipology tipology;
	private String priority;
	private LocalDate dataSegnalazione;
	private LocalDate dataObiettivo;
	private LocalDate dataChiusura;
	private String comune;
	private String indirizzo;
	private LatLng coordinate;
	private OperationCenter operationCenter;

	public Operation(String id, OperationTipology tipology, LatLng coordinate, OperationCenter operationCenter) {
		this.id = id;
		this.tipology = tipology;
		this.coordinate = coordinate;
		this.operationCenter = operationCenter;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OperationTipology getTipology() {
		return tipology;
	}

	public void setTipology(OperationTipology tipology) {
		this.tipology = tipology;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public LocalDate getDataSegnalazione() {
		return dataSegnalazione;
	}

	public void setDataSegnalazione(LocalDate dataSegnalazione) {
		this.dataSegnalazione = dataSegnalazione;
	}

	public LocalDate getDataObiettivo() {
		return dataObiettivo;
	}

	public void setDataObiettivo(LocalDate dataObiettivo) {
		this.dataObiettivo = dataObiettivo;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public LatLng getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(LatLng coordinate) {
		this.coordinate = coordinate;
	}

	public OperationCenter getOperationCenter() {
		return operationCenter;
	}

	public void setOperationCenter(OperationCenter operationCenter) {
		this.operationCenter = operationCenter;
	}

	public LocalDate getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(LocalDate dataChiusura) {
		this.dataChiusura = dataChiusura;
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
