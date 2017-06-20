package it.polito.centraletelefonica.model;

import java.util.LinkedHashMap;
import java.util.Map;

import it.polito.centraletelefonica.db.CentraleDAO;
import it.polito.centraletelefonica.db.Database;
import it.polito.centraletelefonica.db.DatabaseType;
import it.polito.centraletelefonica.db.OperatoreDAO;
import it.polito.centraletelefonica.db.OperazioneDAO;

public class Model {

	private static final String DB_CENTRALE = "centraletelefonica";
	private static OperatoreDAO operatoreDAO;
	private static OperazioneDAO operazioneDAO;
	private static CentraleDAO centraleDAO;
	private static Map<String, Database> databases;

	private static void loadDatabaseResources() {

		Database dbCentrale = new Database(DB_CENTRALE, DatabaseType.MYSQL);
		getDatabases().put(DB_CENTRALE, dbCentrale);
		getCentraleDAO().addDatabase(dbCentrale);
		getOperatoreDAO().addDatabase(dbCentrale);
		getOperazioneDAO().addDatabase(dbCentrale);

	}

	private static Map<String, Database> getDatabases() {

		if (databases == null) {
			databases = new LinkedHashMap<>();
		}

		return databases;
	}

	private static OperatoreDAO getOperatoreDAO() {

		if (operatoreDAO == null) {
			operatoreDAO = new OperatoreDAO();
		}

		return operatoreDAO;
	}

	private static OperazioneDAO getOperazioneDAO() {

		if (operazioneDAO == null) {
			operazioneDAO = new OperazioneDAO();
		}

		return operazioneDAO;
	}

	private static CentraleDAO getCentraleDAO() {

		if (centraleDAO == null) {
			centraleDAO = new CentraleDAO();
		}

		return centraleDAO;
	}

}
