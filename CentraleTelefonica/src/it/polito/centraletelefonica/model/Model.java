package it.polito.centraletelefonica.model;

import it.polito.centraletelefonica.db.OperationCenterDAO;
import it.polito.centraletelefonica.db.OperationCenterDAOImplementation;
import it.polito.centraletelefonica.db.OperationDAO;
import it.polito.centraletelefonica.db.OperationDAOImplementation;
import it.polito.centraletelefonica.db.WorkerDAO;
import it.polito.centraletelefonica.db.WorkerDAOImplementation;

public class Model {

	private static WorkerDAO workerDao;
	private static OperationCenterDAO opererationCenterDao;
	private static OperationDAO operationDao;
	
	private static OperationCenterDAOImplementation getOperationCenterDao() {
		
		if (opererationCenterDao == null) {
			opererationCenterDao = new OperationCenterDAOImplementation();
			
		}

		return (OperationCenterDAOImplementation) opererationCenterDao;
	}

	private static OperationDAOImplementation getOperationDao() {
		
		if (operationDao == null) {
			operationDao = new OperationDAOImplementation();
		}
		
		return (OperationDAOImplementation) operationDao;
	}

	private static WorkerDAOImplementation getWorkerDao() {
		
		if (workerDao == null) {
			workerDao = new WorkerDAOImplementation();
		}
		
		return (WorkerDAOImplementation) workerDao;
	}

}
