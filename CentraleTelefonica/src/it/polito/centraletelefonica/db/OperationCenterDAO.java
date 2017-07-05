package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.OperationCenter;

public interface OperationCenterDAO extends DAO {

	public int insert(OperationCenter operationCenter);

	public int update(OperationCenter operationCenter);

	public int delete(int operationCenterId);

}
