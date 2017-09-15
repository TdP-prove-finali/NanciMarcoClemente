package it.polito.centraletelefonica.model;

public class OperationCenter {

	private String name, id, street;
	private Position position;

	public OperationCenter(String name, String street) {
		this.setName(name);
		this.street = street;
		this.setPosition(street);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(String street) {
		this.position = new Position("Torino", street);
	}

	public String getStreet() {
		return street;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
