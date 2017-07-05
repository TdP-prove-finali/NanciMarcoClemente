package it.polito.centraletelefonica.db;

import it.polito.centraletelefonica.model.Operation;

public interface OperationDAO extends DAO {

	/**
	 * Insert a new operation record
	 * 
	 * @param operation
	 *            operation to ins
	 * @return code state for the process
	 */

	public int insert(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int updateType(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int updatePriority(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int updateUrgency(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int updateCity(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int updateCordinates(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int updateOperationCenter(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int updateOperationWorker(Operation operation);

	/**
	 * 
	 * @param operation
	 * @return
	 */

	public int delete(int operation);

}
