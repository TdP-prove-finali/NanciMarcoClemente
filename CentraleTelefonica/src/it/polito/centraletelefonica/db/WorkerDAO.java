package it.polito.centraletelefonica.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.centraletelefonica.model.OperationCenter;
import it.polito.centraletelefonica.model.Worker;

public class WorkerDAO extends DAO {

	public WorkerDAO(Database database) {
		super(database);
	}

	public List<Worker> getAllWorkers(Map<String, OperationCenter> centers) {

		List<Worker> workers = new LinkedList<>();
		ResultSet resultSet = executeQuery(MySQLQueries.GET_ALL_WORKERS, getDatabase().getConnection());

		try {

			while (resultSet.next()) {
				String nome = resultSet.getString("Nominativo").split(" ")[0];
				String cognome = resultSet.getString("Nominativo").split(" ")[1];
				Worker worker = new Worker(resultSet.getString("OperatoreID"), nome, cognome);
				worker.setNumeroChiusure(resultSet.getInt("NumeroChiusure"));
				workers.add(worker);
				worker.setCentraleServizio(centers.get(resultSet.getString("CentraleID")));
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return workers;
	}

	public int insert(Worker worker) {
		Object[] parameters = new Object[2];
		parameters[0] = worker.getId();
		parameters[1] = worker.getFullName();
		System.out.println(parameters[1]);
		return executeUpdate(MySQLQueries.INSERT_NEW_WORKER, parameters, getDatabase().getConnection());
	}

	public int update(Worker worker) {
		Object[] parameters = new Object[2];
		parameters[0] = worker.getFullName();
		parameters[1] = worker.getId();
		return executeUpdate(MySQLQueries.UPDATE_WORKER_NAME, parameters, getDatabase().getConnection());
	}

	public int delete(int workerId) {
		Object[] parameters = new Object[1];
		parameters[0] = workerId;
		return executeUpdate(MySQLQueries.UPDATE_WORKER_NAME, parameters, getDatabase().getConnection());
	}

}
