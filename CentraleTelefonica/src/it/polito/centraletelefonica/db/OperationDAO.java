package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Operation;

public interface OperationDAO {

	public int insert(Operation operation);

	public int update(Operation operation);

	public int delete(int operation);

}
