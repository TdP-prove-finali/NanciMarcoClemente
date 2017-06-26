package it.polito.centraletelefonica.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MySQLDatabase implements Database {

	private ComboPooledDataSource dataSource;
	private String dbName;

	public MySQLDatabase(String dbName) {
		this.setDbName(dbName);
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public Connection getConnection() {

		if (dataSource == null) {
			dataSource = new ComboPooledDataSource();
		}

		try {
			return dataSource.getConnection();
		}

		catch (SQLException e) {
			// TODO: eccezione da gestire
			e.printStackTrace();
		}

		return null;
	}

}
