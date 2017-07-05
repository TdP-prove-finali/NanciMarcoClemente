package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Worker;

public class WorkerDAOImplementation implements WorkerDAO {

	private MySQLDatabase database;

	@Override
	public int insert(Worker worker) {
		Object[] parameters = new Object[2];
		parameters[0] = worker.getIdWorker();
		parameters[1] = worker.getFullName();
		return executeUpdate(MySQLQueries.INSERT_NEW_WORKER, parameters, database.getConnection());
	}

	@Override
	public int update(Worker worker) {
		Object[] parameters = new Object[2];
		parameters[0] = worker.getFullName();
		parameters[1] = worker.getIdWorker();
		return executeUpdate(MySQLQueries.UPDATE_WORKER_NAME, parameters, database.getConnection());
	}

	@Override
	public int delete(int workerId) {
		Object[] parameters = new Object[1];
		parameters[0] = workerId;
		return executeUpdate(MySQLQueries.UPDATE_WORKER_NAME, parameters, database.getConnection());
	}

}
