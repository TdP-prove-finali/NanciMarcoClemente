package it.polito.centraletelefonica.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.google.maps.model.LatLng;

import it.polito.centraletelefonica.model.OperationCenter;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class OperationCenterDAO extends DAO {

	public List<OperationCenter> getAllOperationCenter() {
		List<OperationCenter> result = new LinkedList<>();
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.GET_ALL_CENTERS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("CentraleID");
				String nome = resultSet.getString("Nome");
				String indirizzo = resultSet.getString("Indirizzo");
				double lat = resultSet.getDouble("Latitude");
				double lng = resultSet.getDouble("Longitude");
				LatLng coordinate = new LatLng(lat, lng);
				int operatori = resultSet.getInt("NumeroOperatori");
				OperationCenter op = new OperationCenter(id, nome, indirizzo, coordinate, operatori);
				result.add(op);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void insertCenter(OperationCenter op) {
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.INSERT_CENTER);
			preparedStatement.setString(1, op.getId());
			preparedStatement.setString(2, op.getName());
			preparedStatement.setString(3, op.getStreet());
			preparedStatement.setDouble(4, op.getLatLng().lat);
			preparedStatement.setDouble(5, op.getLatLng().lng);
			preparedStatement.setInt(6, op.getNumOperatori());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Data> initPieArea() {

		List<Data> result = new LinkedList<>();
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.GET_ALL_CENTERS);
			ResultSet set = preparedStatement.executeQuery();
			int elements = 0;
			while (set.next()) {
				elements++;
			}
			double part = 100 / elements;
			set.beforeFirst();
			while (set.next()) {
                Data d = new Data(set.getString("Nome"), part);
                result.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
