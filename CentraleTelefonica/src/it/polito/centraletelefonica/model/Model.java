package it.polito.centraletelefonica.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import it.polito.centraletelefonica.controller.MapJS;
import it.polito.centraletelefonica.db.OperationCenterDAO;
import it.polito.centraletelefonica.db.OperationDAO;
import it.polito.centraletelefonica.db.TipologieDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

public class Model {

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

		if (periodoSelezionato.compareToIgnoreCase("Trimestre") == 0)
			return operationDAO.getNuoveTrimestre();

		if (periodoSelezionato.compareToIgnoreCase("Quadimestre") == 0)
			return operationDAO.getNuoveQuadrimestre();

		return operationDAO.getNuoveSemestre();
	}

	public static ObservableList<OperationCenter> getAllCenters() {
		OperationCenterDAO dao = new OperationCenterDAO();
		return FXCollections.observableArrayList(dao.getAllOperationCenter());
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
		return MapJS.initMap();
	}

}
