package it.polito.centraletelefonica.model;

public class OperationTipology {
	
	private String tipo;
	private double media;
	private double varianza;
	private int operatoriRichiesti;

	public OperationTipology(String tipo, double media,double varianza, int operatoriRichiesti) {
		this.tipo = tipo;
		this.media = media;
		this.varianza = varianza;
		this.operatoriRichiesti = operatoriRichiesti;
	}

	public String getTipo() {
		return tipo;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		OperationTipology other = (OperationTipology) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	

}
