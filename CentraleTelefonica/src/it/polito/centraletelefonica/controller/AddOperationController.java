package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import it.polito.centraletelefonica.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class AddOperationController extends Controller {

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
	private Button btnPaths;

	@FXML
	private TextField txtId;

	@FXML
	private ComboBox<String> boxTipo;

	@FXML
	private ComboBox<String> boxPrio;

	@FXML
	private TextField txtIndirizzo;

	@FXML
	private Button btnCrea;

	@FXML
	void creaOperazione(ActionEvent event) {

		String id = txtId.getText();
		String tipo = boxTipo.getValue();
		String prio = boxPrio.getValue();
		String indirizzo = txtIndirizzo.getText();
		String responseIndirizzo = model.validateIndirizzo(indirizzo);
		boolean check = true;

		if (id.isEmpty()) {
			showAlert("Devi inserire un id");
			check = false;
		}

		if (tipo == null) {
			showAlert("Devi selezionare un tipo di operazione");
			check = false;
		}

		if (prio == null) {
			showAlert("Devi selezionare una priorita'");
			check = false;
		}

		if (responseIndirizzo != "OK") {
			showAlert(responseIndirizzo);
			check = false;
		}


		if (check) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Vuoi aggiungere la nuova operazione?");
			Optional<ButtonType> btn = alert.showAndWait();
			if (btn.get() == ButtonType.OK) {
				Alert confAlert = new Alert(AlertType.INFORMATION);
				confAlert.setHeaderText("Operazione aggiunta");
				model.creaOperazione(id, tipo, prio, indirizzo);
				confAlert.show();
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
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert boxTipo != null : "fx:id=\"boxTipo\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert boxPrio != null : "fx:id=\"boxPrio\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert txtIndirizzo != null : "fx:id=\"txtIndirizzo\" was not injected: check your FXML file 'AddOperation.fxml'.";
		assert btnCrea != null : "fx:id=\"btnCrea\" was not injected: check your FXML file 'AddOperation.fxml'.";

		boxTipo.getItems().addAll(Model.getAllType());
		boxPrio.getItems().addAll("Bassa", "Media", "Alta");

	}
}
