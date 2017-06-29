package it.polito.centraletelefonica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDatabase implements Database {

	private static final String TYPE = "MY_SQL";
	private Connection connection;
	private String dbName, path;
	private Properties properties;

	public MySQLDatabase(String dbName, Properties properties) {
		this.setProperties(properties);
		this.setDbName(dbName);
		setPath(dbName);
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String dbName) {
		this.path = Database.MYSQL_PATH + dbName;
	}

	@Override
	public Connection getConnection() {

		if (connection == null) {
			try {
				connection = DriverManager.getConnection(path, properties);
				connection = new PersistentConnection(connection);
				return connection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return connection;
	}

	@Override
	public String toString() {
		return String.join(" ", dbName, TYPE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dbName == null) ? 0 : dbName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MySQLDatabase other = (MySQLDatabase) obj;
		if (dbName == null) {
			if (other.dbName != null)
				return false;
		} else if (!dbName.equals(other.dbName))
			return false;
		return true;
	}

}
