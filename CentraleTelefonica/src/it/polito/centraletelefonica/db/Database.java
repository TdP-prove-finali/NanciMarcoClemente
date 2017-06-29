package it.polito.centraletelefonica.db;

import java.sql.Connection;

public interface Database {

	final String MYSQL_PATH = "jdbc:mysql://localhost/";
	final String JDBC_URL = "JDBC_URL";
	final String USERNAME = "USERNAME";
	final String PASSWORD = "PASSWORD";

	public Connection getConnection();

}
