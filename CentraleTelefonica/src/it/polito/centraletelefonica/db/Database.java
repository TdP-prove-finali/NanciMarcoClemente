package it.polito.centraletelefonica.db;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Database {

	private ComboPooledDataSource dataSource;
	private static final String URL_SUFFIX = "URL";
	private static final String USER_SUFFIX = "Username";
	private static final String PASSWORD_SUFFIX = "Password";
	private String url, username, password, name;
	private DatabaseType type;

	public Database(String name, DatabaseType type) {
		this.name = name;
		this.type = type;
		setProperties(name);
	}

	private void setProperties(String name) {

		Properties config = new Properties();
		InputStream inputStream = Database.class.getResourceAsStream("dbconfig.properties");

		try {
			config.load(inputStream);
			url = config.getProperty(name + URL_SUFFIX);
			username = config.getProperty(name + USER_SUFFIX);
			password = config.getProperty(name + PASSWORD_SUFFIX);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() throws SQLException, IOException, PropertyVetoException, ClassNotFoundException {

		switch (type) {

		case MYSQL:
			return getMysqlConnection();

		default:
			return null;
		}

	}

	private Connection getMysqlConnection()
			throws SQLException, IOException, PropertyVetoException, ClassNotFoundException {

		if (dataSource == null) {

			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(Class.forName("com.mysql.jdbc.Driver").getName());
			dataSource.setJdbcUrl(url);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			dataSource.setMaxPoolSize(50);
			dataSource.setMaxStatementsPerConnection(5);
			return dataSource.getConnection();
		}

		return dataSource.getConnection();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Database other = (Database) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Database [name=" + name + "]";
	}

}
