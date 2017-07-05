package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.OperationCenter;

public interface OperationCenterDAO extends DAO {

	public int insert(OperationCenter operationCenter);

	public int updateName(OperationCenter operationCenter);
	
	public int updateCordinates(OperationCenter operationCenter);

	public int delete(String operationCenterId);

}
