package it.polito.centraletelefonica.model;

import com.google.maps.model.LatLng;

public class OperationCenter extends Nodo {

	private String id;
	private String name;
	private String street;
	private LatLng latLng;
	private int numOperatori;

	public OperationCenter(String id, String name, String street, LatLng latLng, int numOperatori) {
		this.id = id;
		this.name = name;
		this.street = street;
		this.latLng = latLng;
		this.numOperatori = numOperatori;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	public int getNumOperatori() {
		return numOperatori;
	}

	public void setNumOperatori(int numOperatori) {
		this.numOperatori = numOperatori;
	}

	@Override
	public String toString() {
		return name;
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
		OperationCenter other = (OperationCenter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
