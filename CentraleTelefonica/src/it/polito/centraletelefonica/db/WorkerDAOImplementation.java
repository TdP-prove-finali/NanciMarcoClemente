package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Worker;

public class WorkerDAOImplementation implements WorkerDAO {

	private MySQLDatabase database;

	@Override
	public int insert(Worker worker) {
		Object[] parameters = new Object[2];
		parameters[0] = worker.getIdWorker();
		parameters[1] = worker.getFirstname();
		return executeUpdate(MySQLQueries.INSERT_NEW_WORKER, parameters, database.getConnection());
	}

	@Override
	public int update(Worker worker) {
		return 0;
		// TODO Auto-generated method stub

	}

	@Override
	public int delete(int workerId) {
		return workerId;
		// TODO Auto-generated method stub

	}

}
