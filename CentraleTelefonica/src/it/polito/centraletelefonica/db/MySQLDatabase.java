package it.polito.centraletelefonica.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MySQLDatabase implements Database {

	private static final String TYPE = "MY_SQL";
	private ComboPooledDataSource dataSource;
	private String dbName;
	private Properties properties;

	public MySQLDatabase(String dbName, Properties properties) {
		this.setProperties(properties);
		this.setDbName(dbName);
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

	@Override
	public Connection getConnection() {

		if (dataSource == null) {
			
			dataSource = new ComboPooledDataSource();
			dataSource.setJdbcUrl(properties.getProperty(Database.JDBC_URL));
			dataSource.setUser(properties.getProperty(Database.USERNAME));
			dataSource.setPassword(properties.getProperty(Database.PASSWORD));
			
			try {
				return dataSource.getConnection();
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
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
