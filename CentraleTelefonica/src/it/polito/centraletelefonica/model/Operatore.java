package it.polito.centraletelefonica.model;

public class Operatore {

	private String stato;
	private OperationCenter center;
	private String nome;

	public Operatore(String nome) {
		this.nome = nome;
	}

	/**
	 * <p>
	 * Stati:
	 * </p>
	 * <ul>
	 * <li>libero</li>
	 * <li>occupato</li>
	 * <li>in pausa</li>
	 * <li>in viaggio</li>
	 * </ul>
	 * 
	 * @param stato
	 */
	public void setStato(String stato) {
		this.stato = stato;

	}

	/**
	 * setta la centrale di appartenenza dell'operatore
	 * 
	 * @param center
	 */
	public void setCenter(OperationCenter center) {
		this.center = center;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Operatore other = (Operatore) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
