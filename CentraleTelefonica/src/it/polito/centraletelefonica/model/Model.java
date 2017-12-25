package it.polito.centraletelefonica.model;

import java.time.LocalDate;

import it.polito.centraletelefonica.db.OperationCenterDAO;
import it.polito.centraletelefonica.db.OperationDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class Model {

	public void getAreaPercentage(LocalDate from, LocalDate to, ObservableList<Data> dati) {
		OperationDAO operationDAO = new OperationDAO();
		operationDAO.getAreaPercentage(from, to, dati);
	}

	public void getTypePercentage(LocalDate from, LocalDate to, ObservableList<Data> dati) {
		OperationDAO dao = new OperationDAO();
		dao.setTypePercentage(from, to, dati);
	}

	public ObservableList<ChiusureRow> getChiusure(String periodoSelezionato) {

		ObservableList<ChiusureRow> result = FXCollections.observableArrayList();
		OperationDAO operationDAO = new OperationDAO();

		if (periodoSelezionato.compareToIgnoreCase("Mese") == 0) {
			result.addAll(operationDAO.getChiuseMese());
			double mediaSuMese = operationDAO.getMediaChiuseMese();
			for (ChiusureRow chiusureRow : result) {
				chiusureRow.setMediaMese(Math.ceil(mediaSuMese));
				double diff = Math.ceil((chiusureRow.getOpConcluse() - mediaSuMese) / 100);
				chiusureRow.setDiffPunti(diff);
			}
		}

		return result;
	}

	public ObservableList<NuoveRow> getNuove(String periodoSelezionato) {

		ObservableList<NuoveRow> result = FXCollections.observableArrayList();
		OperationDAO operationDAO = new OperationDAO();

		if (periodoSelezionato.compareToIgnoreCase("Mese") == 0) {
			result.addAll(operationDAO.getNuoveMese());
			double mediaSuMese = operationDAO.mediaSuMese();
			for (NuoveRow nuoveRow : result) {
				nuoveRow.setMediaMese(Math.ceil(mediaSuMese));
				double diff = Math.ceil((nuoveRow.getNuoveSegnalazioni() - mediaSuMese) / 100);
				nuoveRow.setDiffPunti(diff);
			}
			return result;
		}

		if (periodoSelezionato.compareToIgnoreCase("Trimestre") == 0)
			return operationDAO.getNuoveTrimestre();

		if (periodoSelezionato.compareToIgnoreCase("Quadimestre") == 0)
			return operationDAO.getNuoveQuadrimestre();

		return operationDAO.getNuoveSemestre();
	}

	public static ObservableList<OperationCenter> getAllCenters() {
		OperationCenterDAO dao = new OperationCenterDAO();
		return FXCollections.observableArrayList(dao.getAllOperationCenter());
	}

}
