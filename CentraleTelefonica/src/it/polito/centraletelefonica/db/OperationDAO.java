package it.polito.centraletelefonica.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import it.polito.centraletelefonica.model.ChiusureRow;
import it.polito.centraletelefonica.model.NuoveRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class OperationDAO extends DAO {

	public void getAreaPercentage(LocalDate from, LocalDate to, ObservableList<Data> dati) {

		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.N_OP_CENTRALE_PERIODO);
			preparedStatement.setDate(1, Date.valueOf(from));
			preparedStatement.setDate(2, Date.valueOf(to));
			ResultSet resultSet = preparedStatement.executeQuery();
			int tot = 0;

			while (resultSet.next()) {
				tot += resultSet.getInt("n_operazioni");
			}

			resultSet.beforeFirst();
			int count = 0;

			while (resultSet.next()) {

				double percentage = (double) (resultSet.getInt("n_operazioni")) / tot;
				int parte = (int) (percentage * 100);
				dati.get(count).setPieValue(parte);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setTypePercentage(LocalDate from, LocalDate to, ObservableList<Data> dati) {

		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.N_OP_TIPO_PERIODO);
			preparedStatement.setDate(1, Date.valueOf(from));
			preparedStatement.setDate(2, Date.valueOf(to));
			ResultSet resultSet = preparedStatement.executeQuery();
			int tot = 0;

			while (resultSet.next()) {
				tot += resultSet.getInt("n_operazioni");
			}

			resultSet.beforeFirst();
			int count = 0;

			while (resultSet.next()) {

				double percentage = (double) (resultSet.getInt("n_operazioni")) / tot;
				int parte = (int) (percentage * 100);
				dati.get(count).setPieValue(parte);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ObservableList<NuoveRow> getNuoveMese() {
		ObservableList<NuoveRow> result = FXCollections.observableArrayList();
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.NUOVE_PER_MESE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				NuoveRow nuoveRow = new NuoveRow();
				nuoveRow.setPeriodo(resultSet.getString("Mese"));
				nuoveRow.setNuoveSegnalazioni(resultSet.getInt("n_operazioni"));
				result.add(nuoveRow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ObservableList<NuoveRow> getNuoveTrimestre() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<NuoveRow> getNuoveQuadrimestre() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<NuoveRow> getNuoveSemestre() {
		// TODO Auto-generated method stub
		return null;
	}

	public double mediaSuMese() {
		double result = 0.0;
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.MEDIA_NUOVE_MENSILE);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			result = resultSet.getDouble("avg_mensile");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ObservableList<ChiusureRow> getChiuseMese() {
		ObservableList<ChiusureRow> result = FXCollections.observableArrayList();
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.CHIUSURE_PER_MESE);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ChiusureRow cRow = new ChiusureRow();
				cRow.setPeriodo(resultSet.getString("Mese"));
				cRow.setOpConcluse(resultSet.getInt("n_chiusure"));
				result.add(cRow);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public double getMediaChiuseMese() {

		double result = 0.0;
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.MEDIA_CHIUSE_MESE);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			result = resultSet.getDouble("avg_mensile");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
