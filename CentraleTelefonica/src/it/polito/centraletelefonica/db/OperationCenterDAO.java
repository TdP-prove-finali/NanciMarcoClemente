package it.polito.centraletelefonica.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.google.maps.model.LatLng;

import it.polito.centraletelefonica.model.OperationCenter;

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

}
