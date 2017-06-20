package it.polito.centraletelefonica.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

	private String query;
	private QueryType type;
	private Object[] wildcards;

	/**
	 * Constructor for a simple query
	 * 
	 * @param query
	 *            query to run
	 * @param type
	 *            type of query
	 */

	public Query(String query, QueryType type) {
		this.query = query;
		this.type = type;
	}

	/**
	 * Constructor for a query that needs a prepared statement
	 * 
	 * @param query
	 *            query to run
	 * @param type
	 *            type of query
	 * @param wildcards
	 *            parameters for prepared statement
	 */

	public Query(String query, QueryType type, Object[] wildcards) {
		this.query = query;
		this.type = type;
		this.wildcards = wildcards;
	}

	public final Object executeQuery(Connection connection) {

		if (type == QueryType.SELECT) {
			return executeSelectQuery(connection);
		}

		return executeUpdateQuery(connection);

	}

	private Object executeSelectQuery(Connection connection) {

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			if (wildcards != null) {
				for (int i = 0; i < wildcards.length; i++) {
					ps.setObject(i + 1, wildcards[i]);
				}
			}

			ResultSet rs = ps.executeQuery();
			return rs;

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Object executeUpdateQuery(Connection connection) {

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			if (wildcards != null) {
				for (int i = 0; i < wildcards.length; i++) {
					ps.setObject(i + 1, wildcards[i]);
				}
			}

			return ps.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String toString() {
		return query + " " + type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((query == null) ? 0 : query.hashCode());
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
		Query other = (Query) obj;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		return true;
	}

}
