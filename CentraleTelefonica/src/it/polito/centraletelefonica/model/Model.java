package it.polito.centraletelefonica.model;

import java.time.LocalDate;

import it.polito.centraletelefonica.db.OperationDAO;

public class Model {

	public int getClosedOperation(LocalDate from, LocalDate to) {
		OperationDAO operationDAO = new OperationDAO();
		return operationDAO.getClosedOperationCount(from, to);
	}

	public int getOpenedOperation(LocalDate from, LocalDate to) {
		OperationDAO operationDAO = new OperationDAO();
		return operationDAO.getOpenedOperationCount(from, to);
	}

	public String getClosedBefore(LocalDate from, LocalDate to, String periodoPrecedente, int chiusurePeriodo,
			boolean closing) {

		from = periodoPrecedente.compareTo("Settimana") == 0 ? from.minusWeeks(1) : from;
		from = periodoPrecedente.compareTo("Mese") == 0 ? from.minusMonths(1) : from;
		from = periodoPrecedente.compareTo("Trimestre") == 0 ? from.minusMonths(3) : from;
		from = periodoPrecedente.compareTo("Quadrimestre") == 0 ? from.minusMonths(4) : from;
		from = periodoPrecedente.compareTo("Semestre") == 0 ? from.minusMonths(6) : from;
		from = periodoPrecedente.compareTo("Anno") == 0 ? from.minusYears(1) : from;
		to = periodoPrecedente.compareTo("Settimana") == 0 ? from.minusWeeks(1) : to;
		to = periodoPrecedente.compareTo("Mese") == 0 ? from.minusMonths(1) : to;
		to = periodoPrecedente.compareTo("Trimestre") == 0 ? from.minusMonths(3) : to;
		to = periodoPrecedente.compareTo("Quadrimestre") == 0 ? from.minusMonths(4) : to;
		to = periodoPrecedente.compareTo("Semestre") == 0 ? from.minusMonths(6) : to;
		to = periodoPrecedente.compareTo("Anno") == 0 ? from.minusYears(1) : to;
		OperationDAO operationDAO = new OperationDAO();
		int chiusurePrecedenti = closing ? operationDAO.getClosedOperationCount(from, to)
				: operationDAO.getOpenedOperationCount(from, to);
		double percentuale = (double) (chiusurePeriodo - chiusurePrecedenti) / chiusurePrecedenti;
		percentuale = Double.isNaN(percentuale) ? 0 : percentuale;
		return String.valueOf(percentuale).concat("%");
	}

}
