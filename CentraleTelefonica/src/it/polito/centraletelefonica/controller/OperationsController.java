package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.polito.centraletelefonica.model.Model;
import it.polito.centraletelefonica.model.Operation;
import javafx.collections.FXCollections;
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

public class OperationsController extends Controller {

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
    private Button btnAddOp;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private TableView<Operation> table;

	@FXML
	private TableColumn<Operation, String> colID;

	@FXML
	private TableColumn<Operation, String> colType;

	@FXML
	private TableColumn<Operation, String> colPriority;

	@FXML
	private TableColumn<Operation, Double> colAverage;

	@FXML
	private TableColumn<Operation, Double> colDeviation;

	@FXML
	private TableColumn<Operation, LocalDate> colReporting;

	@FXML
	private TableColumn<Operation, LocalDate> colGoal;

	@FXML
	private TableColumn<Operation, LocalDate> colClosing;

	@FXML
	private TableColumn<Operation, String> colStatus;

	@FXML
	private TableColumn<Operation, String> colCity;

	@FXML
	private TableColumn<Operation, String> colIndirizzo;

	@FXML
	private TableColumn<Operation, String> colCenter;

	@FXML
	private TableColumn<Operation, Integer> colOperatoriRIchiesti;

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
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert btnWorkers != null : "fx:id=\"btnWorkers\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'OperationsView.fxml'.";
        assert btnAddOp != null : "fx:id=\"btnAddOp\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colID != null : "fx:id=\"colID\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colType != null : "fx:id=\"colType\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colPriority != null : "fx:id=\"colPriority\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colAverage != null : "fx:id=\"colAverage\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colDeviation != null : "fx:id=\"colDeviation\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colReporting != null : "fx:id=\"colReporting\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colGoal != null : "fx:id=\"colGoal\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colClosing != null : "fx:id=\"colClosing\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colStatus != null : "fx:id=\"colStatus\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colCity != null : "fx:id=\"colCity\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colIndirizzo != null : "fx:id=\"colIndirizzo\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colCenter != null : "fx:id=\"colCenter\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colOperatoriRIchiesti != null : "fx:id=\"colOperatoriRIchiesti\" was not injected: check your FXML file 'OperationsView.fxml'.";

		colID.setCellValueFactory(new PropertyValueFactory<Operation, String>("id"));
		colType.setCellValueFactory(new PropertyValueFactory<Operation, String>("tipo"));
		colPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
		colAverage.setCellValueFactory(new PropertyValueFactory<>("media"));
		colDeviation.setCellValueFactory(new PropertyValueFactory<>("varianza"));
		colReporting.setCellValueFactory(new PropertyValueFactory<>("dataSegnalazione"));
		colGoal.setCellValueFactory(new PropertyValueFactory<>("dataObiettivo"));
		colClosing.setCellValueFactory(new PropertyValueFactory<>("dataChiusura"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("stato"));
		colCity.setCellValueFactory(new PropertyValueFactory<>("comune"));
		colIndirizzo.setCellValueFactory(new PropertyValueFactory<>("indirizzo"));
		colCenter.setCellValueFactory(new PropertyValueFactory<>("operationCenter"));
		colOperatoriRIchiesti.setCellValueFactory(new PropertyValueFactory<>("operatoriRichiesti"));

		table.getItems().addAll(FXCollections.observableArrayList(Model.getAllOperations()));

	}
}
