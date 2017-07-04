package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Worker;

public interface WorkerDAO extends DAO {

	public int insert(Worker worker);

	public int update(Worker worker);

	public int delete(int workerId);

}
