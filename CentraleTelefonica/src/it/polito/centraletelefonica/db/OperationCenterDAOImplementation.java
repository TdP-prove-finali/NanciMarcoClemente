package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.OperationCenter;

public class OperationCenterDAOImplementation implements OperationCenterDAO {

	private MySQLDatabase database;

	@Override
	public int insert(OperationCenter operationCenter) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operationCenter.getOperationCenterID();
		statementParameters[1] = operationCenter.getOperationCenterName();
		return executeUpdate(MySQLQueries.INSERT_NEW_OPERATION_CENTER, statementParameters, database.getConnection());
	}

	@Override
	public int updateName(OperationCenter operationCenter) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operationCenter.getOperationCenterName();
		statementParameters[1] = operationCenter.getOperationCenterID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_CENTER_NAME, statementParameters, database.getConnection());
	}

	@Override
	public int updateCordinates(OperationCenter operationCenter) {
		Object[] statementParameters = new Object[4];
		statementParameters[0] = operationCenter.getOperationCenterStreet();
		statementParameters[1] = operationCenter.getCordinates().lat;
		statementParameters[2] = operationCenter.getCordinates().lng;
		statementParameters[3] = operationCenter.getOperationCenterID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_CENTER_CORDINATES, statementParameters,
				database.getConnection());
	}

	@Override
	public int delete(String operationCenterId) {
		Object[] statementParameters = new Object[1];
		statementParameters[0] = operationCenterId;
		return executeUpdate(MySQLQueries.DELETE_OPERATION_CENTER, statementParameters, database.getConnection());
	}

}
