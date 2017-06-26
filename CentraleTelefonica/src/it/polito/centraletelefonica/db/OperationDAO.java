package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Operation;

public interface OperationDAO {

	public void insert(Operation operation);

	public void update(Operation operation);

	public void delete(int operation);

}
