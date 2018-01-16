package it.polito.centraletelefonica.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.centraletelefonica.controller.MapJS;
import it.polito.centraletelefonica.db.OperationCenterDAO;
import it.polito.centraletelefonica.db.OperationDAO;
import it.polito.centraletelefonica.db.TipologieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class Model {

	private static MioGrafo grafo;
	private static Map<String, OperationCenter> centrali = new HashMap<String, OperationCenter>();
	private static Map<String, Operation> operazioni = new HashMap<String, Operation>();

	public static boolean connectionAvaible() {

		try {
			URL url = new URL("http://www.google.com");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			return con.getResponseCode() == 200;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void getAreaPercentage(LocalDate from, LocalDate to, ObservableList<Data> dati) {
		OperationDAO operationDAO = new OperationDAO();
		operationDAO.getAreaPercentage(from, to, dati);
	}

	public void getTypePercentage(LocalDate from, LocalDate to, ObservableList<Data> dati) {
		OperationDAO dao = new OperationDAO();
		dao.setTypePercentage(from, to, dati);
	}

	public ObservableList<ChiusureRow> getChiusure(String periodoSelezionato) {

		ObservableList<ChiusureRow> result = FXCollections.observableArrayList();
		OperationDAO operationDAO = new OperationDAO();

		if (periodoSelezionato.compareToIgnoreCase("Mese") == 0) {
			result.addAll(operationDAO.getChiuseMese());
			double mediaSuMese = operationDAO.getMediaChiuseMese();
			for (ChiusureRow chiusureRow : result) {
				chiusureRow.setMediaMese(Math.ceil(mediaSuMese));
				double diff = Math.ceil((chiusureRow.getOpConcluse() - mediaSuMese) / 100);
				chiusureRow.setDiffPunti(diff);
			}
		}

		if (periodoSelezionato.compareToIgnoreCase("Trimestre") == 0) {
			result.addAll(operationDAO.getChiuseTrimestre());
			double mediaSuTrimestre = operationDAO.getMediaChiuseTrimestre();
			for (ChiusureRow chiusureRow : result) {
				chiusureRow.setMediaMese(Math.ceil(mediaSuTrimestre));
				double diff = Math.ceil((chiusureRow.getOpConcluse() - mediaSuTrimestre) / 100);
				chiusureRow.setDiffPunti(diff);
			}
		}

		result.addAll(operationDAO.getChiuseQuadrimestre());
		double mediaSuQuadrimestre = operationDAO.getMediaChiuseQuadrimestre();
		for (ChiusureRow chiusureRow : result) {
			chiusureRow.setMediaMese(mediaSuQuadrimestre);
			double diff = Math.ceil((chiusureRow.getOpConcluse() - mediaSuQuadrimestre) / 100);
			chiusureRow.setDiffPunti(diff);
		}

		return result;
	}

	public ObservableList<NuoveRow> getNuove(String periodoSelezionato) {

		ObservableList<NuoveRow> result = FXCollections.observableArrayList();
		OperationDAO operationDAO = new OperationDAO();

		if (periodoSelezionato.compareToIgnoreCase("Mese") == 0) {
			result.addAll(operationDAO.getNuoveMese());
			double mediaSuMese = operationDAO.mediaSuMese();
			for (NuoveRow nuoveRow : result) {
				nuoveRow.setMediaMese(Math.ceil(mediaSuMese));
				double diff = Math.ceil((nuoveRow.getNuoveSegnalazioni() - mediaSuMese) / 100);
				nuoveRow.setDiffPunti(diff);
			}
			return result;
		}

		if (periodoSelezionato.compareToIgnoreCase("Trimestre") == 0) {

			result.addAll(operationDAO.getNuoveTrimestre());
			double mediaSuTrimestre = operationDAO.mediaSuTrimestre();
			for (NuoveRow nuoveRow : result) {
				nuoveRow.setMediaMese(Math.ceil(mediaSuTrimestre));
				double diff = Math.ceil((nuoveRow.getNuoveSegnalazioni() - mediaSuTrimestre) / 100);
				nuoveRow.setDiffPunti(diff);
			}
			return result;

		}

		if (periodoSelezionato.compareToIgnoreCase("Quadrimestre") == 0) {

			result.addAll(operationDAO.getNuoveQuadrimestre());
			double mediaSuQuadrimestre = operationDAO.mediaSuQuadrimestre();
			for (NuoveRow nuoveRow : result) {
				nuoveRow.setMediaMese(Math.ceil(mediaSuQuadrimestre));
				double diff = Math.ceil((nuoveRow.getNuoveSegnalazioni() - mediaSuQuadrimestre) / 100);
				nuoveRow.setDiffPunti(diff);
			}
			return result;

		}

		return result;
	}

	public static ObservableList<OperationCenter> getAllCenters() {
		OperationCenterDAO dao = new OperationCenterDAO();
		return FXCollections.observableArrayList(dao.getAllOperationCenter(centrali));
	}

	public String validateCenterId(String id) {

		if (id == null)
			return "ID NULL";
		if (id.isEmpty())
			return "ID EMPTY";
		if (id.length() != 6)
			return "LENGTH_ERROR";
		if (!isDigit(id.substring(0, 5)))
			return "FIRST_NOT_DIGIT";
		if (!isLetter("" + id.toCharArray()[id.length() - 1]))
			return "LAST NOT LETTER ERROR";

		return "OK";
	}

	private boolean isLetter(String text) {
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(text);
		return m.matches();
	}

	private boolean isDigit(String text) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(text);
		return m.matches();
	}

	public String validateNome(String nome) {

		if (nome == null)
			return "NOME NULL";
		if (nome.isEmpty())
			return "NOME EMPTY";
		if (nome.substring(0, 3).compareTo("TO-") != 0)
			return "INVALID NOME PREFIX";

		return "OK";
	}

	public String validateIndirizzo(String indirizzo) {

		if (indirizzo == null)
			return "INDIRIZZO NULL";
		if (indirizzo.isEmpty())
			return "INDIRIZZO EMPTY";

		String[] tokens = indirizzo.split(" ");

		if (tokens.length < 3)
			return "TIPO OR STREET MANCANTE";

		String tipo = tokens[0];

		if (tipo.compareToIgnoreCase("via") != 0 && tipo.compareToIgnoreCase("corso") != 0
				&& tipo.compareToIgnoreCase("str.") != 0 && tipo.compareToIgnoreCase("piazza") != 0)
			return "TIPO INDIRIZZO NON VALIDO";

		if (!isDigit(tokens[tokens.length - 1]))
			return "CIVICO MANCANTE";

		return "OK";
	}

	public String validateNumOperatori(String numOp) {

		if (numOp == null)
			return "NUM OP NULL";
		if (numOp.isEmpty())
			return "NUM OP EMPTY";
		if (!isDigit(numOp))
			return "NUM NOT DIGIT";

		return "OK";
	}

	public void addCentrale(String id, String nome, String indirizzo, String numOp) {

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBTt64RteMQQxOH5hpCYTcrANObd5QNmr8")
					.build();
			GeocodingResult[] results = GeocodingApi.geocode(context, indirizzo).await();
			LatLng coo = results[0].geometry.location;
			OperationCenter opCenter = new OperationCenter(id, nome, indirizzo, coo, Integer.parseInt(numOp));
			OperationCenterDAO dao = new OperationCenterDAO();
			dao.insertCenter(opCenter);

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ObservableList<Data> initPieArea() {
		OperationCenterDAO dao = new OperationCenterDAO();
		return FXCollections.observableArrayList(dao.initPieArea());
	}

	public static ObservableList<Data> initPieType() {
		TipologieDAO dao = new TipologieDAO();
		return FXCollections.observableArrayList(dao.initPieType());
	}

	public static String initMap() {
		return MapJS.initMap(centrali);
	}

	public static List<Operation> getAllOperations() {
		OperationDAO dao = new OperationDAO();
		return dao.getAll();
	}

	public static List<String> getAllType() {
		OperationDAO dao = new OperationDAO();
		return dao.getAllType();
	}

	public void creaOperazione(String id, String tipo, String prio, String indirizzo) {

		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBTt64RteMQQxOH5hpCYTcrANObd5QNmr8")
					.build();
			GeocodingResult[] results = GeocodingApi.geocode(context, indirizzo).await();
			LatLng coo = results[0].geometry.location;
			OperationCenter center = getCloserCenter(coo);
			Operation operation = new Operation(id, coo, center);
			operation.setTipo(tipo);
			operation.setPriority(prio);
			operation.setIndirizzo(indirizzo);
			operation.setDataSegnalazione(LocalDate.now());
			LocalDate ob = null;
			ob = tipo == "Bassa" ? LocalDate.now().plusDays(3) : LocalDate.now();
			ob = tipo == "Media" ? LocalDate.now().plusDays(2) : LocalDate.now();
			ob = tipo == "Alta" ? LocalDate.now().plusDays(1) : LocalDate.now();
			operation.setDataObiettivo(ob);
			operation.setDataChiusura(LocalDate.of(0, 1, 1));
			operation.setComune("Torino");
			operation.setStato("Open");
			OperationDAO dao = new OperationDAO();
			dao.insertOperation(operation);

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private OperationCenter getCloserCenter(LatLng coo) {

		List<OperationCenter> list = new LinkedList<>(new OperationCenterDAO().getAllOperationCenter(centrali));
		OperationCenter result = null;

		for (Iterator<OperationCenter> iterator = list.iterator(); iterator.hasNext();) {
			OperationCenter operationCenter = iterator.next();
			double minDistance = Double.MAX_VALUE;
			double xPowDist = Math.pow(coo.lat - operationCenter.getLatLng().lat, 2);
			double yPowDist = Math.pow(coo.lng - operationCenter.getLatLng().lng, 2);
			double dist = Math.sqrt(xPowDist + yPowDist);

			if (minDistance > dist) {
				minDistance = dist;
				result = operationCenter;
			}

		}

		return result;
	}

	public List<Operation> getOperationsFrom(LocalDate from) {
		OperationDAO dao = new OperationDAO();
		return dao.getOperationFrom(from);
	}

	public List<Operation> getOperationsTo(LocalDate to) {
		OperationDAO dao = new OperationDAO();
		return dao.getOperationTo(to);
	}

	public List<Operation> getOperationsFromTo(LocalDate from, LocalDate to) {
		OperationDAO dao = new OperationDAO();
		return dao.getOperationBetween(from, to, operazioni);
	}

	public static String addMarkers(LocalDate value) {

		String res = MapJS.addMarkers(value);
		grafo = new MioGrafo();
		OperationDAO dao = new OperationDAO();
		OperationCenterDAO centerDao = new OperationCenterDAO();
		List<Operation> operazioniLista = new LinkedList<>(
				dao.getOperationBetween(value, value.minusDays(5), operazioni));
		Graphs.addAllVertices(grafo, operazioniLista);
		Graphs.addAllVertices(grafo, centerDao.getAllOperationCenter(centrali));
		archiCentraliOperazioni(value);
		archiOperazioneOperazione();
		setPesi();

		return res;
	}

	private static void setPesi() {

		for (Nodo source : grafo.vertexSet()) {
			for (Nodo target : grafo.vertexSet()) {
				DefaultWeightedEdge edge = grafo.getEdge(source, target);
				double peso = 0;
				if ((source instanceof OperationCenter) && (target instanceof Operation)) {
					OperationCenter center = (OperationCenter) source;
					Operation operation = (Operation) target;
					peso = pesoCentraleOperazione(center, operation);
					grafo.setEdgeWeight(edge, peso);
				}
				if ((source instanceof Operation) && (target instanceof Operation)) {
					Operation op1 = (Operation) source;
					Operation op2 = (Operation) target;
					peso = pesoOpOp(op1, op2);
					grafo.setEdgeWeight(edge, peso);
				}

			}
		}

	}

	private static double pesoOpOp(Operation op1, Operation op2) {
		double peso = 0;
		double distance = LatLngTool.distance(
				new com.javadocmd.simplelatlng.LatLng(op1.getCoordinate().lat, op1.getCoordinate().lng),
				new com.javadocmd.simplelatlng.LatLng(op2.getCoordinate().lat, op2.getCoordinate().lng),
				LengthUnit.METER);
		// Supponiamo una velocita' di 14 m/s, un po' piu' di 50 km/h.
		double secondi = distance / 14;
		peso = secondi + op2.getMedia() * 60;
		// peso anche in base al numero di operatori richiesti
		peso = peso * op2.getOperatoriRichiesti();
		return peso;
	}

	private static double pesoCentraleOperazione(OperationCenter center, Operation operation) {

		double peso = 0;
		double distance = LatLngTool.distance(
				new com.javadocmd.simplelatlng.LatLng(center.getLatLng().lat, center.getLatLng().lng),
				new com.javadocmd.simplelatlng.LatLng(operation.getCoordinate().lat, operation.getCoordinate().lng),
				LengthUnit.METER);
		// Supponiamo una velocita' di 14 m/s, un po' piu' di 50 km/h.
		double secondi = distance / 14;
		peso = secondi + operation.getMedia() * 60;
		// peso anche in base al numero di operatori richiesti
		peso = peso * operation.getOperatoriRichiesti();
		return peso;
	}

	private static void archiOperazioneOperazione() {

		for (Operation operazioneSource : operazioni.values()) {

			for (Operation operazioneTarget : operazioni.values()) {
				if (!grafo.containsEdge(operazioneSource, operazioneTarget))
					grafo.addEdge(operazioneSource, operazioneTarget);
			}
		}

	}

	private static void archiCentraliOperazioni(LocalDate value) {

		OperationCenterDAO dao = new OperationCenterDAO();

		for (OperationCenter center : centrali.values()) {
			OperationCenter centerVertex = centrali.get(center.getId());
			List<Operation> edges = new LinkedList<>(dao.getLinkedOperations(centerVertex, value, operazioni));

			for (Operation operation : edges) {
				Operation vertex = operazioni.get(operation.getId());
				if (!grafo.containsEdge(centerVertex, vertex)) {
					grafo.addEdge(centerVertex, vertex);
				}

			}

		}

	}

	public static void test() {

	}

}
