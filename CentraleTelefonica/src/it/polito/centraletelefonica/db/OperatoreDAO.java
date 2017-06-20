package it.polito.centraletelefonica.db;

import java.util.Map;

import it.polito.centraletelefonica.model.Operatore;

public class OperatoreDAO implements DAO {

	private static Map<Database, Database> dbMap;

	@Override
	public void insert(Object obj) {
		Query query = new Query("", QueryType.INSERT_INTO, null);
		Operatore op = (Operatore) obj;

	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getObject(Object primaryKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadAll() {
		// TODO Auto-generated method stub

	}

}
