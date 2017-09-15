package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import it.polito.centraletelefonica.model.Model;
import it.polito.centraletelefonica.model.OperationCenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
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
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private Button btnPaths;

	@FXML
	private TableView<OperationCenter> table;

	@FXML
	private TableColumn<OperationCenter, String> colId;

	@FXML
	private TableColumn<OperationCenter, String> colNome;

	@FXML
	private TableColumn<OperationCenter, String> colIndirizzo;

	private static final ObservableList<OperationCenter> emptyRow = FXCollections
			.observableArrayList(new OperationCenter("", ""));

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
			table.getItems().addAll(emptyRow);
		}

		if (event.getCode().equals(KeyCode.BACK_SPACE)) {

			// TODO: riflettere il cambiamento sul db

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATTENZIONE");
			alert.setContentText("Proseguendo eliminerai una riga dalla tabella, premere OK solo se convinti");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				OperationCenter opCenter = table.getSelectionModel().getSelectedItem();
				table.getItems().remove(opCenter);
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
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnWorkers != null : "fx:id=\"btnWorkers\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'OverView.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert colNome != null : "fx:id=\"colNome\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		assert colIndirizzo != null : "fx:id=\"colIndirizzo\" was not injected: check your FXML file 'OperationCenterView.fxml'.";
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("name"));
		colIndirizzo.setCellValueFactory(new PropertyValueFactory<>("street"));
		table.getItems().addAll(FXCollections.observableList(Model.getAllCenters()));
	}
}
