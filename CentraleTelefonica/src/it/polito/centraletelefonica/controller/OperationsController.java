package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.mchange.v1.lang.holders.VolatileBooleanHolder;

import it.polito.centraletelefonica.model.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
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
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private TableView<Operation> table;

	@FXML
	private TableColumn<Operation, String> colID;

	@FXML
	private TableColumn<Operation, LocalDate> colReporting;

	@FXML
	private TableColumn<Operation, String> colPriority;

	// Empty line
	private final ObservableList<Operation> data = FXCollections
			.observableArrayList(new Operation(String.valueOf(0), LocalDate.now(), "False"));

	@FXML
	void openRelativeAnalitycs(MouseEvent event) {

		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {

			Node node = (Node) event.getSource();
			String nodeID = node.getId();
			changeScene(nodeID);

		}

	}

	@FXML
	void search(KeyEvent event) {

		if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
			System.out.println("Search");
		}

	}

	/**
	 * Add an empty line when key enter is pressed
	 * 
	 * @param event
	 */

	@FXML
	void addRow(KeyEvent event) {

		if (event.getEventType().equals(KeyEvent.KEY_PRESSED) && event.getCode().equals(KeyCode.ENTER)) {
			table.getItems().addAll(data);
		}

	}

	@FXML
	void initialize() {
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert btnWorkers != null : "fx:id=\"btnWorkers\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colID != null : "fx:id=\"colID\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colReporting != null : "fx:id=\"colReporting\" was not injected: check your FXML file 'OperationsView.fxml'.";
		assert colPriority != null : "fx:id=\"colPriority\" was not injected: check your FXML file 'OperationsView.fxml'.";
		colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		colReporting.setCellValueFactory(new PropertyValueFactory<>("reportingDate"));
		colPriority.setCellValueFactory(new PropertyValueFactory<>("urgency"));

	}
}
