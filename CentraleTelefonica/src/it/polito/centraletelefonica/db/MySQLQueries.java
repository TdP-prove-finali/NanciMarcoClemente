package it.polito.centraletelefonica.db;

@SuppressWarnings("all")

final class MySQLQueries {

	// *** INSERT SECTION ***

	/**
	 * <p> Insert new worker </p> <p> Parameters: </p> <ul> <li> <p> IDOperatore
	 * </p> </li> <li> <p> Nome </p> </li> </ul>
	 * 
	 */

	static final String INSERT_NEW_WORKER = "insert into operatore(IDOperatore,Nome) values(?,?)";

	/**
	 * <p> Insert new operation </p> <p> Parameters: </p> <ul> <li> <p>
	 * IDSegnalazione </p> </li> <li> <p> Segnalazione-Tipo </p> </li> <li> <p>
	 * Priority </p> </li> <li> <p> DataSegnalazione </p> </li> <li> <p> Urgenza
	 * </p> </li> <li> <p> Comune </p> </li> <li> <p> Indirizzo </p> </li> <li>
	 * <p> IDOperatore </p> </li> <li> <p> Operatore </p> </li> <li> <p>
	 * IDCentrale </p> </li> <li> <p> Centrale </p> </li> </ul>
	 * 
	 */

	static final String INSERT_NEW_OPERATION = "insert into operazione(IDSegnalazione,Segnalazione-Tipo,Priority,DataSegnalazione,Urgenza,Comune,Indirizzo,IDOperatore,Operatore,IDCentrale,Centrale) values(?,?,?,?,?,?,?,?,?,?,?)";

	/**
	 * <p> Insert new operation center </p> <p> Parameters: </p> <ul> <li> <p>
	 * IDCentrale </p> </li> <li> <p> Nome </p> </li> <li> <p> Indirizzo </p>
	 * </li> <li> <p> Latitude </p> </li> <li> <p> Longitude </p> </li> </ul>
	 * 
	 */

	static final String INSERT_NEW_OPERATION_CENTER = "insert into centrale(IDCentrale,Nome,Indirizzo,Latitude,Longitude) values(?,?,?,?,?)";

	// *** UPDATE SECTION ***

	/**
	 * <p> Updates worker name </p> <p> Parameters: </p> <ul> <li> <p> Nome </p>
	 * </li> <li> <p> IDOperatore </p> </li> </ul>
	 * 
	 */

	static final String UPDATE_WORKER_NAME = "update operatore set Nome = ? where IDOperatore = ?";

	/**
	 * <p> Updates operation center name </p> <p> Parameters: </p> <ul> <li> <p>
	 * Nome </p> </li> <li> <p> IDCentrale </p> </li> </ul>
	 * 
	 */

	static final String UPDATE_OPERATION_CENTER_NAME = "update centrale set Nome = ? where IDCentrale = ?";

	/**
	 * <p> Updates operation center cordinates </p> <p> Parameters: </p>
	 * <ul><li><p>Indirizzo</p></li> <li> <p> Latitude </p> </li> <li> <p>
	 * Longitude </p> </li> <li> <p> IDCentrale </p> </li> </ul>
	 * 
	 */

	static final String UPDATE_OPERATION_CENTER_CORDINATES = "update centrale set Indirizzo = ? Latitude = ?, Longitude = ? where IDCentrale = ?";

	/**
	 * <p>Updates operation type</p><p> Parameters: </p>
	 * <ul><li><p>Tipo-Operazione</p></li><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String UPDATE_OPERATION_TYPE = "update operazione set Segnalazione-Tipo = ? where SegnalazioneID = ?";

	/**
	 * <p>Updates operation priority</p><p> Parameters: </p>
	 * <ul><li><p>Priority</p></li><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String UPDATE_OPERATION_PRIORITY = "update operazione set Priority = ? where SegnalazioneID = ?";

	/**
	 * <p>Updates operation urgency</p><p> Parameters: </p>
	 * <ul><li><p>Urgenza</p></li><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String UPDATE_OPERATION_URGENCY = "update operazione set Urgenza = ? where SegnalazioneID = ?";

	/**
	 * <p>Updates operation city</p><p> Parameters: </p>
	 * <ul><li><p>Comune</p></li><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String UPDATE_OPERATION_CITY = "update operazione set Comune = ? where SegnalazioneID = ?";

	/**
	 * <p>Updates operation cordinates</p><p> Parameters: </p>
	 * <ul><li><p>Indirizzo</p></li><li><p>Latitude</p></li><li><p>Longitude</p></li><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String UPDATE_OPERATION_CORDINATES = "update operazione set Indirizzo = ?, Latitude = ?, Longitude = ? where SegnalazioneID = ?";

	/**
	 * <p>Updates operation operation center</p><p> Parameters: </p>
	 * <ul><li><p>CentraleID</p></li><li><p>Centrale</p></li><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String UPDATE_OPERATION_OPERATION_CENTER = "update operazione set CentraleID = ?, Centrale = ? where SegnalazioneID = ?";

	/**
	 * <p>Updates operation worker</p><p> Parameters: </p>
	 * <ul><li><p>OperatoreID</p></li><li><p>Operatore</p></li><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String UPDATE_OPERATION_WORKER = "update operazione set OperatoreID = ?, Operatore = ? where SegnalazioneID = ?";

	// *** DELETE SECTION ***

	/**
	 * <p>Delete a worker record</p> <p>Parameters:</p>
	 * <ul><li><p>OperatoreID</p></li></ul>
	 */

	static final String DELETE_WORKER = "delete from operatore where OperatoreID = ?";

	/**
	 * <p>Delete an operation record</p> <p>Parameters:</p>
	 * <ul><li><p>SegnalazioneID</p></li></ul>
	 */

	static final String DELETE_OPERATION = "delete from operazione where SegnalazioneID = ?";

	/**
	 * <p>Delete an woperation center record</p> <p>Parameters:</p>
	 * <ul><li><p>CentraleID</p></li></ul>
	 */

	static final String DELETE_OPERATION_CENTER = "delete from centrale where CentraleID = ?";

}
