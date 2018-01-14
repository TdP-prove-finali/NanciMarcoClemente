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

	public static final String NUOVE_PER_MESE = "select t.mese, count(t.mese) n_operazioni \r\n"
			+ "from operazioni, tempo t\r\n" + "where DataSegnalazione = t.`data`\r\n" + "group by t.mese \r\n"
			+ "order by t.mese";

	public static final String NUOVE_PER_TRIMESTRE = "select t.trimestre, count(t.trimestre) n_operazioni \r\n"
			+ "from operazioni, tempo t \r\n" + "where DataSegnalazione = t.`data` \r\n" + "group by t.trimestre \r\n"
			+ "order by t.trimestre";

	public static final String NUOVE_PER_QUADRIMESTRE = "select t.quadrimestre, count(t.quadrimestre) n_operazioni \r\n"
			+ "from operazioni, tempo t \r\n" + "where DataSegnalazione = t.`data` \r\n"
			+ "group by t.quadrimestre \r\n" + "order by t.quadrimestre";

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

	public static final String MEDIA_NUOVE_MENSILE = "select sum(sub1.op_mese)/12 avg_mensile \r\n" + "from(\r\n"
			+ "select count(t.mese) op_mese \r\n" + "from operazioni, tempo t \r\n"
			+ "where DataSegnalazione = t.`data`\r\n" + "group by t.mese\r\n" + "order by t.mese) sub1;";

	public static final String MEDIA_NUOVE_TRIMESTRE = "select sum(sub1.op_trimestre)/4 avg_trimestre \r\n"
			+ "from(\r\n" + "select count(t.trimestre) op_trimestre \r\n" + "from operazioni, tempo t \r\n"
			+ "where DataSegnalazione = t.`data` \r\n" + "group by t.trimestre \r\n" + "order by t.trimestre) sub1;";

	public static final String MEDIA_NUOVE_QUADRIMESTRE = "select sum(sub1.op_trimestre)/4 avg_quadrimestre\r\n"
			+ "from(select count(t.quadrimestre) op_trimestre \r\n" + "from operazioni, tempo t \r\n"
			+ "where DataSegnalazione = t.`data` \r\n" + "group by t.quadrimestre \r\n"
			+ "order by t.quadrimestre) sub1;";

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

	public static final String CHIUSURE_PER_MESE = "select t.mese, count(t.mese) n_chiusure \r\n"
			+ "from operazioni, tempo t\r\n" + "where DataChiusura <= DataObiettivo \r\n"
			+ "and DataSegnalazione = t.`data`\r\n" + "group by t.mese \r\n" + "order by t.mese;";

	public static final String CHIUSURE_PER_TRIMESTRE = "select t.trimestre, count(t.trimestre) n_chiusure \r\n"
			+ "from operazioni, tempo t \r\n" + "where DataChiusura <= DataObiettivo \r\n"
			+ "and DataSegnalazione = t.`data` \r\n" + "group by t.trimestre \r\n" + "order by t.trimestre;";

	public static final String CHIUSURE_PER_QUADRIMESTRE = "select t.quadrimestre, count(t.quadrimestre) n_chiusure \r\n"
			+ "from operazioni, tempo t \r\n" + "where DataChiusura <= DataObiettivo \r\n"
			+ "and DataSegnalazione = t.`data` \r\n" + "group by t.quadrimestre \r\n" + "order by t.quadrimestre;";

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

	public static final String MEDIA_CHIUSE_MESE = "select sum(sub1.op_mese)/12 avg_mensile \r\n" + "from(\r\n"
			+ "select count(t.mese) op_mese \r\n" + "from operazioni, tempo t\r\n"
			+ "where DataChiusura <= DataObiettivo\r\n" + "and DataSegnalazione = t.`data`\r\n" + "group by t.mese\r\n"
			+ "order by t.mese) sub1;";

	public static final String MEDIA_CHIUSE_TRIMESTRE = "select sum(sub1.op_trimestre)/4 avg_trimestrale\r\n"
			+ "from(\r\n" + "select count(t.trimestre) op_trimestre from operazioni, tempo t\r\n"
			+ "where DataChiusura <= DataObiettivo \r\n" + "and DataSegnalazione = t.`data` \r\n"
			+ "group by t.mese\r\n" + "order by t.mese) sub1;";

	public static final String MEDIA_CHIUSE_QUADRIMESTRE = "select sum(sub1.op_trimestre)/4 avg_quadrimestre\r\n"
			+ "from(\r\n" + "select count(t.quadrimestre) op_trimestre from operazioni, tempo t\r\n"
			+ "where DataChiusura <= DataObiettivo \r\n" + "and DataSegnalazione = t.`data` \r\n"
			+ "group by t.quadrimestre \r\n" + "order by t.quadrimestre) sub1;";

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

	/**
	 * SIMPLE SELECT FROM TIPOLOGIE<br>
	 * Risultati:<br>
	 * <ol>
	 * <li>tipo</li>
	 * <li>durata_media</li>
	 * <li>varianza</li>
	 * <li>operatori_richiesti</li>
	 * </ol>
	 */
	public static final String GET_ALL_TYPE = "select * from tipologie";

	public static final String GET_ALL_OPERATIONS = "select *\r\n" + "from operazioni op,tipologie,centrali c\r\n"
			+ "where SegnalazioneTipo = tipo\r\n" + "and op.CentraleID=c.CentraleID;";

	public static final String INSERT_OPERATION = "insert into operazioni(SegnalazioneID,SegnalazioneTipo,Priority,DataSegnalazione,DataObiettivo,DataChiusura,Stato,Comune,Indirizzo,Latitude,Longitude,CentraleID) values(?,?,?,?,?,?,?,?,?,?,?,?);";

	public static final String GET_OPERATION_FROM = "select * \r\n" + "from operazioni op, tipologie t, centrali c\r\n"
			+ "where DataSegnalazione >= ? and SegnalazioneTipo = t.tipo and op.CentraleID = c.CentraleID";

	public static final String GET_OPERATION_TO = "select * from operazioni op, tipologie "
			+ "	where DataSegnalazione <= ? and SegnalazioneTipo = t.tipo and op.CentraleID = c.CentraleID";

	public static final String GET_OPERATION_BETWEEN = "select * from operazioni op, tipologie t, centrali c"
			+ "	where DataSegnalazione between ? and ? and SegnalazioneTipo = t.tipo and op.CentraleID = c.CentraleID;";

	public static final String GET_OPENED_OPERATION = "select * \r\n"
			+ "from tipologie ti, operazioni op, tempo t, centrali c\r\n"
			+ "where op.SegnalazioneTipo = ti.tipo and op.DataSegnalazione = t.`data`\r\n"
			+ "and op.CentraleID = c.CentraleID\r\n" + "and DataSegnalazione between ? and ?\r\n"
			+ "and Stato = \"Open\"";

	public static final String GET_OPERATION_BY_CENTER_DATE = "select * \r\n"
			+ "from operazioni op, centrali c, tempo t, tipologie ti\r\n" + "where op.DataSegnalazione = t.`data`\r\n"
			+ "and op.CentraleID = c.CentraleID\r\n" + "and op.SegnalazioneTipo = ti.tipo\r\n"
			+ "and op.DataSegnalazione between ? and ?\r\n" + "and Stato = \"Open\";";

}
