package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Operation;

public class OperationDAOImplementation implements OperationDAO {

	private MySQLDatabase database;

	@Override
	public int insert(Operation operation) {
		Object[] statementParameters = new Object[3];
		statementParameters[0] = operation.getOperationID();
		statementParameters[1] = operation.getReportingDate();
		statementParameters[2] = operation.getUrgengy();
		return executeUpdate(MySQLQueries.INSERT_NEW_OPERATION, statementParameters, database.getConnection());
	}

	@Override
	public int updateType(Operation operation) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operation.getOperationType();
		statementParameters[1] = operation.getOperationID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_TYPE, statementParameters, database.getConnection());
	}

	@Override
	public int updatePriority(Operation operation) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operation.getPriority();
		statementParameters[1] = operation.getOperationID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_PRIORITY, statementParameters, database.getConnection());
	}

	@Override
	public int updateUrgency(Operation operation) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operation.getUrgengy();
		statementParameters[1] = operation.getOperationID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_URGENCY, statementParameters, database.getConnection());
	}

	@Override
	public int updateCity(Operation operation) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operation.getCity();
		statementParameters[1] = operation.getOperationID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_CITY, statementParameters, database.getConnection());
	}

	@Override
	public int updateCordinates(Operation operation) {
		Object[] statementParameters = new Object[4];
		statementParameters[0] = operation.getStreet();
		statementParameters[1] = operation.getCordinates().lat;
		statementParameters[2] = operation.getCordinates().lng;
		statementParameters[3] = operation.getOperationID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_CENTER_CORDINATES, statementParameters,
				database.getConnection());
	}

	@Override
	public int updateOperationCenter(Operation operation) {
		Object[] statementParameters = new Object[3];
		statementParameters[0] = operation.getOperationCenter().getOperationCenterID();
		statementParameters[1] = operation.getOperationCenter().getOperationCenterName();
		statementParameters[2] = operation.getOperationID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_OPERATION_CENTER, statementParameters,
				database.getConnection());
	}

	@Override
	public int updateOperationWorker(Operation operation) {
		Object[] statementParameters = new Object[2];
		statementParameters[0] = operation.getOperator().getIdWorker();
		statementParameters[1] = operation.getOperator().getFullName();
		statementParameters[2] = operation.getOperationID();
		return executeUpdate(MySQLQueries.UPDATE_OPERATION_WORKER, statementParameters, database.getConnection());
	}

	@Override
	public int delete(int operationID) {
		Object[] statementParameters = new Object[1];
		statementParameters[0] = operationID;
		return executeUpdate(MySQLQueries.DELETE_OPERATION, statementParameters, database.getConnection());
	}

}
