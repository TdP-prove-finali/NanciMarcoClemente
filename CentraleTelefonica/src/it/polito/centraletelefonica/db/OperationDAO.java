package it.polito.centraletelefonica.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OperationDAO extends DAO {

	public int getOpenedOperationCount(LocalDate from, LocalDate to) {

		int result = 0;

		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.OPEND_FORM_TO);
			preparedStatement.setDate(1, Date.valueOf(from));
			preparedStatement.setDate(2, Date.valueOf(to));
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getInt("opened");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getClosedOperationCount(LocalDate from, LocalDate to) {

		int result = 0;

		try {
			PreparedStatement preparedStatement = persistentConnection.prepareStatement(Queries.CLOSED_FROM_TO);
			preparedStatement.setDate(1, Date.valueOf(from));
			preparedStatement.setDate(2, Date.valueOf(to));
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				result = resultSet.getInt("chiusure");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
