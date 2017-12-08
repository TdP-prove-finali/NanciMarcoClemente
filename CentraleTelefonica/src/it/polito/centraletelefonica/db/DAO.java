package it.polito.centraletelefonica.db;

import java.sql.SQLException;

public abstract class DAO {

	protected static PersistentConnection persistentConnection;

	public DAO() {

		if (persistentConnection == null) {
			try {
				persistentConnection = DBConnector.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
