package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.centraletelefonica.model.Model;
import it.polito.centraletelefonica.model.Simulatore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class PathsController extends Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnOverview;

	@FXML
	private Button btnOperations;

	@FXML
	private Button btnOperationsCenter;

	@FXML
	private Button btnWorkers;

	@FXML
	private Button btnPaths;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private WebView webView;

	@FXML
	private Button btnCentrali;

	@FXML
	private Button btnMostraOp;

	@FXML
	private Button btnPercorsi;

	private WebEngine engine;

	@FXML
	void generaPercorsi(ActionEvent event) {
		if (dateFrom.getValue() == null)
			showAlert("Devi selezionare una data per poter visualizzare le operazioni");
		else {
			model.generaPercorsi(dateFrom.getValue());
			showSimulator();
		}
			
	}

	@FXML
	void mostraOp(ActionEvent event) {

		if (dateFrom.getValue() == null)
			showAlert("Devi selezionare una data per poter visualizzare le operazioni");
		else {
			engine.executeScript(Model.addMarkers(dateFrom.getValue()));
			btnPercorsi.setDisable(false);
		}

	}

	@FXML
	void mostraCentrali(ActionEvent event) {
		engine.executeScript(Model.initMap());
	}

	@FXML
	void openRelativeAnalitycs(MouseEvent event) {

		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {

			Node node = (Node) event.getSource();
			String nodeID = node.getId();
			changeScene(nodeID);

		}

	}

	@FXML
	void initialize() {
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert btnWorkers != null : "fx:id=\"btnWorkers\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert webView != null : "fx:id=\"webView\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert btnCentrali != null : "fx:id=\"btnAddMarker\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert btnMostraOp != null : "fx:id=\"btnMostraOp\" was not injected: check your FXML file 'PathsView.fxml'.";
		assert btnPercorsi != null : "fx:id=\"btnDistanze\" was not injected: check your FXML file 'PathsView.fxml'.";

		engine = webView.getEngine();
		if (Model.connectionAvaible()) {
			engine.load(getClass().getResource("map.html").toString());
		}

		else
			engine.load(getClass().getResource("404.html").toString());

	}
}
