package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.centraletelefonica.model.Model;
import it.polito.centraletelefonica.model.OperationCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class OperationsCenterController extends Controller {

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
	private TableView<OperationCenter> table;

	@FXML
	private TableColumn<OperationCenter, String> colId;

	@FXML
	private TableColumn<OperationCenter, String> colNome;

	@FXML
	private TableColumn<OperationCenter, String> colIndirizzo;

	@FXML
	private TableColumn<OperationCenter, Integer> colOperatori;

	@FXML
	void addCenter(ActionEvent event) {

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
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnWorkers != null : "fx:id=\"btnWorkers\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert colNome != null : "fx:id=\"colNome\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert colIndirizzo != null : "fx:id=\"colIndirizzo\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert colOperatori != null : "fx:id=\"colOperatori\" was not injected: check your FXML file 'OperationCenterView.fxml'.";

		colId.setCellValueFactory(new PropertyValueFactory<OperationCenter, String>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<OperationCenter, String>("name"));
		colIndirizzo.setCellValueFactory(new PropertyValueFactory<OperationCenter, String>("street"));
		colOperatori.setCellValueFactory(new PropertyValueFactory<OperationCenter, Integer>("numOperatori"));

		table.setItems(Model.getAllCenters());

	}
}
