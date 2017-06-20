package it.polito.centraletelefonica.db;

import java.util.LinkedHashMap;
import java.util.Map;

public class OperazioneDAO implements DAO {

	private static Map<String, Database> dbMap;

	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub

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

	@Override
	public void addDatabase(Database dataBase) {
		getDbMap().put(dataBase.getName(), dataBase);		
	}
	
     private Map<String,Database> getDbMap() {
    	
    	 if (dbMap == null) {
			dbMap = new LinkedHashMap<>();
		}

		return dbMap;
     }

}
