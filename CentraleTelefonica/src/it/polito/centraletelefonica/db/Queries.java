package it.polito.centraletelefonica.db;

@SuppressWarnings("all")
class Queries {

	/**
	 * <p>
	 * Seleziona il numero di operazioni concluse entro obiettivo nel periodo
	 * selezionato.
	 * </p>
	 * <p>
	 * Parametri:
	 * </p>
	 * <ol>
	 * <li>from: data iniziale</li>
	 * <li>to: data iniziale</li>
	 * <p>
	 * Risultato: chiusure -> rappresenta il numero di operazioni chiuse in tempo in
	 * quel periodo.
	 */
	public static final String CLOSED_FROM_TO = "select count(*) chiusure\r\n" + "from operazioni\r\n"
			+ "where DataSegnalazione between ? and ?\r\n"
			+ "and DataChiusura between DataSegnalazione and DataObiettivo;";

	/**
	 * <p>
	 * Seleziona il numero di nuove selezioni arrivate nel periodo considerato
	 * </p>
	 * <p>
	 * Parametri:
	 * </p>
	 * <ol>
	 * <li>from: data iniziale</li>
	 * <li>to: data finale</li>
	 * </ol>
	 * <p>
	 * Risultato: opened -> rappresenta il numero di nuove segnalazioni nel periodo.
	 */

	public static final String OPEND_FORM_TO = "select count(DataSegnalazione) opened\r\n" + "from operazioni\r\n"
			+ "where DataSegnalazione between ? and ?;";
}
