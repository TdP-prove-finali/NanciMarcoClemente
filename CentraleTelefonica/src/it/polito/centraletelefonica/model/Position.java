package it.polito.centraletelefonica.model;

import com.google.maps.model.LatLng;

public class Position {

	private String city;
	private String address;
	private LatLng cordinates;

	/**
	 * Costruttore per Java Bean.
	 * 
	 * @param city
	 * @param address
	 */

	public Position(String city, String address) {
		this.setCity(city);
		this.setAddress(address);
		this.setCordinates(city, address);
	}

	public void setCordinates(String city, String address) {
		// TODO settare le coordinate
	}

	public LatLng getCordinates() {
		return cordinates;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
