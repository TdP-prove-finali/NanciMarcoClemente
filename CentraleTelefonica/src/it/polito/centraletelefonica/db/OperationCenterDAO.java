package it.polito.centraletelefonica.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.centraletelefonica.model.OperationCenter;

public class OperationCenterDAO extends DAO {

	public OperationCenterDAO(Database database) {
		super(database);
	}

	public List<OperationCenter> getAllCenters(Map<String, OperationCenter> centers) {
		List<OperationCenter> operationCenters = new LinkedList<>();
		ResultSet resultSet = executeQuery(MySQLQueries.GET_ALL_CENTERS, getDatabase().getConnection());

		try {
			while (resultSet.next()) {
				String id = resultSet.getString("CentraleID");
				OperationCenter operationCenter = new OperationCenter(resultSet.getString("Nome"),
						resultSet.getString("Indirizzo"));
				operationCenter.setId(id);
				operationCenters.add(operationCenter);
				centers.put(id, operationCenter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return operationCenters;
	}

	public int insert(OperationCenter operationCenter) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operationCenter.getId();
		statementParameters[1] = operationCenter.getName();
		return executeUpdate(MySQLQueries.INSERT_NEW_OPERATION_CENTER, statementParameters,
				getDatabase().getConnection());
	}

	public int updateName(OperationCenter operationCenter) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operationCenter.getName();
		statementParameters[1] = operationCenter.getId();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_CENTER_NAME, statementParameters,
				getDatabase().getConnection());
	}

	public int updateCordinates(OperationCenter operationCenter) {
		Object[] statementParameters = new Object[4];
		statementParameters[0] = operationCenter.getPosition().getAddress();
		statementParameters[1] = operationCenter.getPosition().getCordinates().lat;
		statementParameters[2] = operationCenter.getPosition().getCordinates().lng;
		statementParameters[3] = operationCenter.getId();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_CENTER_CORDINATES, statementParameters,
				getDatabase().getConnection());
	}

	public int delete(String operationCenterId) {
		Object[] statementParameters = new Object[1];
		statementParameters[0] = operationCenterId;
		return executeUpdate(MySQLQueries.DELETE_OPERATION_CENTER, statementParameters, getDatabase().getConnection());
	}

}
