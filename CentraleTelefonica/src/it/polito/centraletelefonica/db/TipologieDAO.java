package it.polito.centraletelefonica.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.chart.PieChart.Data;

public class TipologieDAO extends DAO {

	public List<Data> initPieType() {

		try {
			List<Data> dati = new LinkedList<>();
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.GET_ALL_TYPE);
			ResultSet set = preparedStatement.executeQuery();
			int elements = 0;
			while (set.next()) {
				elements++;
			}
			set.beforeFirst();
			double parte = 100 / elements;
			while (set.next()) {
				Data d = new Data(set.getString("tipo"), parte);
				dati.add(d);
			}
			return dati;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
