package it.polito.centraletelefonica.model;

public class Worker {

	private String id;
	private String firstname, surname, fullName;
	private int numeroChiusure;
	private OperationCenter centraleServizio;

	public Worker(String idWorker, String firstname, String surname) {
		this.setId(idWorker);
		this.setFirstname(firstname);
		this.setSurname(surname);
		this.setFullName(firstname, surname);
	}

	public String getId() {
		return id;
	}

	public void setId(String idWorker) {
		this.id = idWorker;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String firstname, String surname) {
		this.fullName = String.join(" ", firstname, surname);
	}

	public int getNumeroChiusure() {
		return numeroChiusure;
	}

	public void setNumeroChiusure(int numeroChiusure) {
		this.numeroChiusure = numeroChiusure;
	}

	public OperationCenter getCentraleServizio() {
		return centraleServizio;
	}

	public void setCentraleServizio(OperationCenter centraleServizio) {
		this.centraleServizio = centraleServizio;
	}

	@Override
	public String toString() {
		return String.join(" ", String.valueOf(id), fullName);
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
		Worker other = (Worker) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
