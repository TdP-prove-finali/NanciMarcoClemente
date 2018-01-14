package it.polito.centraletelefonica.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.maps.model.LatLng;

import it.polito.centraletelefonica.model.Operation;
import it.polito.centraletelefonica.model.OperationCenter;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class OperationCenterDAO extends DAO {

	public List<OperationCenter> getAllOperationCenter(Map<String, OperationCenter> centrali) {
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
				if (!centrali.containsKey(id))
					centrali.put(id, op);
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

	public List<Operation> getLinkedOperations(OperationCenter centerVertex, LocalDate value, Map<String, Operation> operazioni) {
		
		
		List<Operation> operations = new LinkedList<>();
		
		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.GET_OPERATION_BY_CENTER_DATE);
			preparedStatement.setDate(1, Date.valueOf(value.minusDays(5)));
			preparedStatement.setDate(2, Date.valueOf(value));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				while (resultSet.next()) {
					String id = resultSet.getString("SegnalazioneID");
					String tipo = resultSet.getString("SegnalazioneTipo");
					double media = resultSet.getDouble("durata_media");
					double varianza = resultSet.getDouble("varianza");
					int operatoriRichiesti = resultSet.getInt("operatori_richiesti");
					LatLng coordinate = new LatLng(resultSet.getDouble("op.Latitude"),
							resultSet.getDouble("op.Longitude"));
					String idCentrale = resultSet.getString("op.CentraleID");
					String name = resultSet.getString("nome");
					String street = resultSet.getString("indirizzo");
					LatLng latLng = new LatLng(resultSet.getDouble("c.Latitude"), resultSet.getDouble("c.Longitude"));
					int numOperatori = resultSet.getInt("NumeroOperatori");
					LocalDate reportingDate = resultSet.getDate("DataSegnalazione").toLocalDate();
					LocalDate goalDate = resultSet.getDate("DataObiettivo").toLocalDate();
					Date d = resultSet.getDate("DataChiusura");
					OperationCenter operationCenter = new OperationCenter(idCentrale, name, street, latLng,
							numOperatori);
					Operation operation = new Operation(id, coordinate, operationCenter);
					LocalDate closingDate = null;
					String stato = resultSet.getString("Stato");
					String city = resultSet.getString("Comune");
					if (d != null) {
						closingDate = d.toLocalDate();
						operation.setDataChiusura(closingDate);
					}

					operation.setDataSegnalazione(reportingDate);
					operation.setDataObiettivo(goalDate);
					operation.setStato(stato);
					operation.setTipo(tipo);
					operation.setMedia(media);
					operation.setComune(city);
					operation.setIndirizzo(resultSet.getString("Indirizzo"));
					operation.setPriority(resultSet.getString("Priority"));
					operation.setVarianza(varianza);
					operation.setOperatoriRichiesti(operatoriRichiesti);
					operations.add(operation);
					
					operazioni.put(id, operation);
					
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return operations;
	}

}
