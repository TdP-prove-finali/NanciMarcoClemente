package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.OperationCenter;

public interface OperationCenterDAO {

	public void insert(OperationCenter operationCenter);

	public void update(OperationCenter operationCenter);

	public void delete(int operationCenterId);

}
