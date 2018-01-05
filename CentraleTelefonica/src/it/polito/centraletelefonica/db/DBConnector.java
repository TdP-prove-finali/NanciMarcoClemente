package it.polito.centraletelefonica.db;

import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnector {

	private static final String JDBC_URL = "jdbc:mysql://localhost/centraletelefonica?user=root&zeroDateTimeBehavior=convertToNull";
	private static ComboPooledDataSource dataSource;
	private static PersistentConnection persistentConnection;

	public static PersistentConnection getConnection() throws SQLException {

		if (persistentConnection == null) {
			dataSource = new ComboPooledDataSource();
			dataSource.setJdbcUrl(JDBC_URL);
			persistentConnection = new PersistentConnection(dataSource.getConnection());
			return persistentConnection;
		}

		return persistentConnection;

	}

}
