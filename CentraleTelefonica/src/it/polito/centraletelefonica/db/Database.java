package it.polito.centraletelefonica.db;

import java.sql.Connection;

public interface Database {
	
	public Connection getConnection();

}
