package it.polito.centraletelefonica.model;

public class Worker {

	private int idWorker;
	private String firstname;
	private String surname;

	public Worker(int idWorker, String firstname, String surname) {
		this.setIdWorker(idWorker);
		this.setFirstname(firstname);
		this.setSurname(surname);
	}

	public int getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(int idWorker) {
		this.idWorker = idWorker;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return String.join(" ", String.valueOf(idWorker), firstname, surname);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idWorker;
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
		Worker other = (Worker) obj;
		if (idWorker != other.idWorker)
			return false;
		return true;
	}
	
	

}
