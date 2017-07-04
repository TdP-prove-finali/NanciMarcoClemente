package it.polito.centraletelefonica.db;

@SuppressWarnings("all")

final class MySQLQueries {

	// *** INSERT SECTION ***

	/**
	 * <p> Insert new worker </p> <p> Values: </p> <ul> <li> <p> IDOperatore
	 * </p> </li> <li> <p> Nome </p> </li> </ul>
	 * 
	 */

	static final String INSERT_NEW_WORKER = "insert into operatore(IDOperatore,Nome) values(?,?)";

	/**
	 * <p> Insert new operation </p> <p> Values: </p> <ul> <li> <p>
	 * IDSegnalazione </p> </li> <li> <p> Segnalazione-Tipo </p> </li> <li> <p>
	 * Priority </p> </li> <li> <p> DataSegnalazione </p> </li> <li> <p> Urgenza
	 * </p> </li> <li> <p> Comune </p> </li> <li> <p> Indirizzo </p> </li> <li>
	 * <p> IDOperatore </p> </li> <li> <p> Operatore </p> </li> <li> <p>
	 * IDCentrale </p> </li> <li> <p> Centrale </p> </li> </ul>
	 * 
	 */

	static final String INSERT_NEW_OPERATION = "insert into operazione(IDSegnalazione,Segnalazione-Tipo,Priority,DataSegnalazione,Urgenza,Comune,Indirizzo,IDOperatore,Operatore,IDCentrale,Centrale) values(?,?,?,?,?,?,?,?,?,?,?)";

	/**
	 * <p> Insert new operation center </p> <p> Values: </p> <ul> <li> <p>
	 * IDCentrale </p> </li> <li> <p> Nome </p> </li> </ul>
	 * 
	 */

	static final String INSERT_NEW_OPERATION_CENTER = "insert into centrale(IDCentrale,Nome) values(?,?)";

	// *** UPDATE SECTION ***

	/**
	 * <p> Updates worker name </p> <p> Values: </p> <ul> <li> <p> Nome </p>
	 * </li> <li> <p> IDOperatore </p> </li> </ul>
	 * 
	 */

	static final String UPDATE_WORKER_NAME = "update operatore set Nome = ? where IDOperatore = ?";

	/**
	 * <p> Updates operation center name </p> <p> Values: </p> <ul> <li> <p>
	 * Nome </p> </li> <li> <p> IDCentrale </p> </li> </ul>
	 * 
	 */

	static final String UPDATE_OPERATION_CENTER_NAME = "update centrale set Nome = ? where IDCentrale = ?";

	/**
	 * <p> Updates operation center street </p> <p> Values: </p> <ul> <li> <p>
	 * Indirizzo </p> </li> <li> <p> IDCentrale </p> </li> </ul>
	 * 
	 */

	static final String UPDATE_OPERATION_CENTER_STREET = "update centrale set Indirizzo = ? where IDCentrale = ?";

	/**
	 * <p> Updates operation center street </p> <p> Values: </p> <ul> <li> <p>
	 * Latitude </p> </li> <li> <p> Longitude </p> </li> <li> <p> IDCentrale
	 * </p> </li> </ul>
	 * 
	 */

	static final String UPDATE_OPERATION_CENTER_CORDINATES = "update centrale set Latitude = ?, Longitude = ? where IDCentrale = ?";
	static final String UPDATE_OPERATION = "";

	// *** DELETE SECTION ***

	static final String DELETE_WORKER = "";
	static final String DELETE_OPERATION = "";
	static final String DELETE_OPERATION_CENTER = "";

}
