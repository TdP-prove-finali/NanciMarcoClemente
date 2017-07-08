package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Worker;

public class WorkerDAO extends DAO {

	public WorkerDAO(Database database) {
		super(database);
	}

	public int insert(Worker worker) {
		Object[] parameters = new Object[2];
		System.out.println("");
		parameters[0] = worker.getIdWorker();
		parameters[1] = worker.getFullName();
		return executeUpdate(MySQLQueries.INSERT_NEW_WORKER, parameters, getDatabase().getConnection());
	}

	public int update(Worker worker) {
		Object[] parameters = new Object[2];
		parameters[0] = worker.getFullName();
		parameters[1] = worker.getIdWorker();
		return executeUpdate(MySQLQueries.UPDATE_WORKER_NAME, parameters, getDatabase().getConnection());
	}

	public int delete(int workerId) {
		Object[] parameters = new Object[1];
		parameters[0] = workerId;
		return executeUpdate(MySQLQueries.UPDATE_WORKER_NAME, parameters, getDatabase().getConnection());
	}

}
