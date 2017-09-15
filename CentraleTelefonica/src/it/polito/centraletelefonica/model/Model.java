package it.polito.centraletelefonica.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import it.polito.centraletelefonica.db.Database;
import it.polito.centraletelefonica.db.MySQLDatabase;
import it.polito.centraletelefonica.db.OperationCenterDAO;
import it.polito.centraletelefonica.db.WorkerDAO;

public class Model {

	private static final String DB_CENTRALE = "centraletelefonica";
	private static Map<String, Database> databases;
	private static Map<String, OperationCenter> centers;

	public static List<Worker> getAllWorkers() {
		WorkerDAO dao = new WorkerDAO(getDatabase(DB_CENTRALE));
		return dao.getAllWorkers(getCenters());
	}

	public static List<OperationCenter> getAllCenters() {
		OperationCenterDAO dao = new OperationCenterDAO(getDatabase(DB_CENTRALE));
		return dao.getAllCenters(getCenters());
	}

	private static Map<String, OperationCenter> getCenters() {

		if (centers == null) {
			centers = new LinkedHashMap<>();
		}

		return centers;
	}

	private static Database getDatabase(String dbCentrale) {

		if (databases == null) {

			databases = new LinkedHashMap<String, Database>();
			databases.put(dbCentrale, new MySQLDatabase(dbCentrale, null));
			return databases.get(dbCentrale);

		}

		if (!databases.containsKey(dbCentrale)) {
			databases.put(dbCentrale, new MySQLDatabase(dbCentrale, null));
			return databases.get(dbCentrale);
		}

		return databases.get(dbCentrale);
	}

	public static void createWorker(String nominativo) {

		String id = "" + nominativo.charAt(0) + nominativo.charAt(3) + nominativo.charAt(5) + nominativo.charAt(7);
		id += "" + ThreadLocalRandom.current().nextInt(1000000, 9999999);
		id = id.replace(" ", "T");
		id = id.toUpperCase();
		Worker worker = new Worker(id, nominativo.split(" ")[0], nominativo.split(" ")[1]);
		WorkerDAO dao = new WorkerDAO(getDatabase(DB_CENTRALE));
		dao.insert(worker);

	}

}
