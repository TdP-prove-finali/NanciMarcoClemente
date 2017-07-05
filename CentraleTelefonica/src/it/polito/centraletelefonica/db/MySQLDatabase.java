package it.polito.centraletelefonica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class MySQLDatabase implements Database {

	private static final String TYPE = "MY_SQL";
	private Map<String, DAO> daos;
	private PersistentConnection persistentConnection;
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

	public void addDao(Map<String, DAO> daos) {
		getDaos().putAll(daos);
	}

	public void addDao(String daoName, DAO dao) {
		if (!getDaos().containsKey(daoName)) {
			getDaos().put(daoName, dao);
		}
	}

	public Map<String, DAO> getDaos() {
		if (daos == null) {
			daos = new LinkedHashMap<>();
		}

		return daos;
	}

	@Override
	public Connection getConnection() {

		if (persistentConnection == null) {
			try {
				persistentConnection = new PersistentConnection(DriverManager.getConnection(path, properties));
				return persistentConnection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return persistentConnection;
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
