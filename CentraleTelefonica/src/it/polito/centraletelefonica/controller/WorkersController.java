package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class WorkersController extends Controller {

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
	private DatePicker dateTo;

	@FXML
	private TableView<?> table;

	@FXML
	private TableColumn<?, ?> colId;

	@FXML
	private TableColumn<?, ?> colNominativo;

	@FXML
	private TableColumn<?, ?> colCentrale;

	@FXML
	private TableColumn<?, ?> colChiusure;

	@FXML
	void createWorker(ActionEvent event) {

	}

	@FXML
	void manageRow(KeyEvent event) {

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
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert btnWorkers != null : "fx:id=\"btnWorkers\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colNominativo != null : "fx:id=\"colNominativo\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colCentrale != null : "fx:id=\"colCentrale\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colChiusure != null : "fx:id=\"colChiusure\" was not injected: check your FXML file 'WorkersView.fxml'.";

	}
}
