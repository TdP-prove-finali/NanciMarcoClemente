package it.polito.centraletelefonica.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO {

	default ResultSet executeQuery(String query, Connection connection) {

		ResultSet resultSet = null;

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;

	}

	default ResultSet executeQuery(String query, Object[] statementParameters, Connection connection) {

		ResultSet resultSet = null;

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			for (int i = 0; i < statementParameters.length; i++) {
				preparedStatement.setObject(i + 1, statementParameters[i]);
			}

			resultSet = preparedStatement.executeQuery();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;

	}

	default int executeUpdate(String query, Object[] statementParameters, Connection connection) {

		int status = 0;

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

}
