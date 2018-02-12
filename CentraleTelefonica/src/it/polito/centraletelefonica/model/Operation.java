package it.polito.centraletelefonica.model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.maps.model.LatLng;

public class Operation extends Nodo {

	private String id;
	private String priority;
	private LocalDate dataSegnalazione;
	private LocalDate dataObiettivo;
	private LocalDate dataChiusura;
	private String comune;
	private String indirizzo;
	private String stato;
	private LatLng coordinate;
	private OperationCenter operationCenter;
	private String tipo;
	private double media;
	private double varianza;
	private int operatoriRichiesti;
	private List<Operatore> richiedenti, titolari;

	public Operation(String id, LatLng coordinate, OperationCenter operationCenter) {
		this.id = id;
		this.coordinate = coordinate;
		this.operationCenter = operationCenter;
		richiedenti = new LinkedList<>();
		titolari = new LinkedList<>();
	}

	public String getTipo() {
		return tipo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public int getOperatoriRichiesti() {
		return operatoriRichiesti;
	}

	public void setOperatoriRichiesti(int operatoriRichiesti) {
		this.operatoriRichiesti = operatoriRichiesti;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void addRichiedente(Operatore operatore) {
		richiedenti.add(operatore);
		if (richiedenti.size() == operatoriRichiesti) {
			setTitolari(richiedenti);
			richiedenti.clear();
		}
	}

	public void setTitolari(List<Operatore> operatori) {
		this.stato = "IN_CORSO";
		titolari.addAll(operatori);
	}

	@Override
	public String toString() {
		return String.join(" ", tipo, indirizzo, dataSegnalazione.toString());
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

	public void liberaOperatori() {

		for (int i = 0; i < titolari.size(); i++) {
			titolari.get(i).setStato("libero");
		}

	}

}
