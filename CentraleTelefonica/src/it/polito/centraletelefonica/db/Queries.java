package it.polito.centraletelefonica.db;

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

	public static final String OPEND_FORM_TO = "select DataSegnalazione, count(DataSegnalazione) n_segn\r\n"
			+ "from operazioni\r\n" + "where DataSegnalazione between ? and ?\r\n" + "group by DataSegnalazione\r\n"
			+ "order by DataSegnalazione;";

	/**
	 * <p>
	 * Seleziona il nome della centrale e il numero di segnalazioni nel periodo
	 * selezionato
	 * </p>
	 * <p>
	 * Parametri:
	 * </p>
	 * <ol>
	 * <li>from: data iniziale</li>
	 * <li>to: data finale</li>
	 * <p>
	 * Risultati:
	 * </p>
	 * <ol>
	 * <li>nome: nome della centrale</li>
	 * <li>n_operazioni: numero di operazioni per la suddetta centrale</li>
	 * </ol>
	 * 
	 */

	public static final String N_OP_CENTRALE_PERIODO = "select nome,count(DataSegnalazione) n_operazioni\r\n"
			+ "from operazioni op, centrali c\r\n" + "where op.CentraleID = c.CentraleID\r\n"
			+ "and op.DataSegnalazione between ? and ?\r\n" + "group by c.CentraleID;";

	/**
	 * <p>
	 * Seleziona il tipo di segnalazione e il numero di segnalazioni di quel tipo
	 * selezionato
	 * </p>
	 * <p>
	 * Parametri:
	 * </p>
	 * <ol>
	 * <li>from: data iniziale</li>
	 * <li>to: data finale</li>
	 * <p>
	 * Risultati:
	 * </p>
	 * <ol>
	 * <li>tipo: tipo di segnalazione</li>
	 * <li>n_operazioni: numero di operazioni per la suddetta tipologia</li>
	 * </ol>
	 * 
	 */

	public static final String N_OP_TIPO_PERIODO = "select t.tipo, count(SegnalazioneTipo) n_operazioni\r\n"
			+ "from operazioni op, tipologie t\r\n" + "where op.SegnalazioneTipo = t.tipo\r\n"
			+ "and op.DataSegnalazione between ? and ?\r\n" + "group by t.tipo;";

	/**
	 * <p>
	 * Seleziona il mese ed il numero di nuove segnalazioni per quel mese
	 * </p>
	 * <p>
	 * Rsiultati:
	 * </p>
	 * <ol>
	 * <li>Mese: mese considerato</li>
	 * <li>n_operazioni: numero di operazioni per quel mese</li>
	 * </ol>
	 */

	public static final String NUOVE_PER_MESE = "select Mese, count(Mese) n_operazioni\r\n" + "from operazioni\r\n"
			+ "group by Mese\r\n" + "order by Mese";

	/**
	 * <p>
	 * Seleziona la media mensile di nuove segnalazioni
	 * </p>
	 * <p>
	 * Risultati
	 * </p>
	 * <ol>
	 * <li>avg_mensile: media mensile</li>
	 * </ol>
	 */

	public static final String MEDIA_NUOVE_MENSILE = "select sum(sub1.op_mese)/12 avg_mensile from(\r\n"
			+ "select count(Mese) op_mese \r\n" + "from operazioni\r\n" + "group by Mese\r\n" + "order by Mese) sub1;";

	/**
	 * <p>
	 * Seleziona il mese ed il numero di chiusure per quel mese
	 * </p>
	 * <p>
	 * Rsiultati:
	 * </p>
	 * <ol>
	 * <li>Mese: mese considerato</li>
	 * <li>n_chiusure: numero di operazioni per quel mese</li>
	 * </ol>
	 */

	public static final String CHIUSURE_PER_MESE = "select Mese, count(Mese) n_chiusure\r\n" + "from operazioni\r\n"
			+ "where DataChiusura <= DataObiettivo\r\n" + "group by Mese\r\n" + "order by Mese;";

	/**
	 * <p>
	 * Seleziona la media mensile di chiusure
	 * </p>
	 * <p>
	 * Risultati
	 * </p>
	 * <ol>
	 * <li>avg_mensile: media mensile</li>
	 * </ol>
	 */

	public static final String MEDIA_CHIUSE_MESE = "select sum(sub1.op_mese)/12 avg_mensile from(\r\n"
			+ "select count(Mese) op_mese from operazioni\r\n" + "where DataChiusura <= DataObiettivo\r\n"
			+ "group by Mese \r\n" + "order by Mese) sub1;";

	/**
	 * <p>
	 * Seleziona tutto dalla tabella centrali
	 * </p>
	 * <p>
	 * Risultati:
	 * </p>
	 * <ol>
	 * <li>CentraleID</li>
	 * <li>Nome</li>
	 * <li>Indirizzo</li>
	 * <li>Latitude</li>
	 * <li>Longitude</li>
	 * <li>NumeroOperatori</li>
	 * </ol>
	 */

	public static final String GET_ALL_CENTERS = "select * from centrali";

	/**
	 * SIMPLE INSERT OF A NEW CENTER<br>
	 * Paramaters:<br>
	 * <ol>
	 * <li>id</li>
	 * <li>nome</li>
	 * <li>indirizzo</li>
	 * <li>latitude</li>
	 * <li>longitude</li>
	 * <li>numeroOperatori</li>
	 * </ol>
	 */

	public static final String INSERT_CENTER = "insert into centrali(CentraleID,Nome,Indirizzo,Latitude,Longitude,NumeroOperatori) values(?,?,?,?,?,?)";

}
