package it.polito.centraletelefonica.db;

import java.sql.Connection;

public class Query {

	private  String query;
	private  QueryType type;

	public Query(String query, QueryType type) {
		this.query = query;
		this.type = type;
	}

	public final Object executeQuery(Connection connection) {

		switch (type) {
		
		case SELECT:
			return executeSelectQuery();
		case UPDATE:
			return executeUpdateQuery();
		case DELETE:
			return executeDeleteQuery();
		case ISERT_INTO:
			return executeInsertQuery();
		
		default:
			break;
		}

		return null;

	}

	private static Object executeSelectQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object executeUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object executeDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object executeInsertQuery() {
		// TODO Auto-generated method stub
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
