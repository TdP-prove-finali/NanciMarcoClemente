package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Worker;

public interface WorkerDAO extends DAO {

	public void insert(Worker worker);

	public void update(Worker worker);

	public void delete(int workerId);

}
