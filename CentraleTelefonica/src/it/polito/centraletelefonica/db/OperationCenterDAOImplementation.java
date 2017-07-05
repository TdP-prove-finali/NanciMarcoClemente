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
	public int update(OperationCenter operationCenter) {
		return 0;
		// TODO Auto-generated method stub

	}

	@Override
	public int delete(int operationCenterId) {
		return 0;
		// TODO Auto-generated method stub

	}

}
