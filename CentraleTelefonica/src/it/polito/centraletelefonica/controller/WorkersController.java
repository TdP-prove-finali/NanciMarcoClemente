package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import it.polito.centraletelefonica.model.Model;
import it.polito.centraletelefonica.model.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
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
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private Button btnPaths;

	@FXML
	private TableView<Worker> table;

	@FXML
	private TableColumn<Worker, String> colId;

	@FXML
	private TableColumn<Worker, String> colNominativo;

	@FXML
	private TableColumn<Worker, String> colCentrale;

	@FXML
	private TableColumn<Worker, Integer> colChiusure;

	// Empty line
	private static final ObservableList<Worker> data = FXCollections.observableArrayList(new Worker("", "", ""));

	@FXML
	void createWorker(TableColumn.CellEditEvent<String, String> event) {
		String nominativo = event.getNewValue();
		Model.createWorker(nominativo);
	}

	/**
	 * Metodo che permette di gestire le righe della tabella. Se il parametro
	 * passato &egrave; il tasto 'ENTER' viene inserita una nuova riga vuota, se
	 * invece viene premuto il tasto 'BACK_SPACE' corrispondente al tasto
	 * elimina viene richiesta conferma per eliminare quella riga.
	 * 
	 * @param event
	 *            tasto premuto
	 */

	@FXML
	void manageRow(KeyEvent event) {

		if (event.getCode().equals(KeyCode.ENTER)) {
			table.getItems().addAll(data);
		}

		if (event.getCode().equals(KeyCode.BACK_SPACE)) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATTENZIONE");
			alert.setContentText("Proseguendo eliminerai una riga dalla tabella, premere OK solo se convinti");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				Worker worker = table.getSelectionModel().getSelectedItem();
				table.getItems().remove(worker);
			}

		}

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
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'OverView.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colNominativo != null : "fx:id=\"colNominativo\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colCentrale != null : "fx:id=\"colCentrale\" was not injected: check your FXML file 'WorkersView.fxml'.";
		assert colChiusure != null : "fx:id=\"colChiusure\" was not injected: check your FXML file 'WorkersView.fxml'.";
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNominativo.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		colChiusure.setCellValueFactory(new PropertyValueFactory<>("numeroChiusure"));
		colCentrale.setCellValueFactory(new PropertyValueFactory<>("centraleServizio"));
		colNominativo.setCellFactory(TextFieldTableCell.forTableColumn());
		List<Worker> workers = new LinkedList<>(Model.getAllWorkers());
		table.getItems().addAll(FXCollections.observableArrayList(workers));

	}
}
