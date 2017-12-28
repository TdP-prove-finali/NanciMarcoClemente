package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddCenterController extends Controller {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtIndirizzo;

	@FXML
	private TextField txtOperatori;

	@FXML
	private Button btnCrea;

	@FXML
	private Button btnOverview;

	@FXML
	private Button btnOperations;

	@FXML
	private Button btnOperationsCenter;

	@FXML
	private Button btnPaths;

	@FXML
	void creaCentrale(ActionEvent event) {

		String id = txtId.getText();
		String nome = txtNome.getText();
		String indirizzo = txtIndirizzo.getText();
		String numOp = txtOperatori.getText();
		String responseId = model.validateCenterId(id);
		String responseNome = model.validateNome(nome);
		String responseIndirizzo = model.validateIndirizzo(indirizzo);
		String responseOperatori = model.validateNumOperatori(numOp);
		boolean check = true;

		if (responseId != "OK") {
			showAlert(responseId);
			check = false;
		}

		if (responseNome != "OK") {
			showAlert(responseNome);
			check = false;
		}
		if (responseIndirizzo != "OK") {
			showAlert(responseIndirizzo);
			check = false;
		}
		if (responseOperatori != "OK") {
			showAlert(responseOperatori);
			check = false;
		}
		
		if (check) {
			model.addCentrale(id,nome,indirizzo,numOp);
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
		assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert txtIndirizzo != null : "fx:id=\"txtIndirizzo\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert txtOperatori != null : "fx:id=\"txtOperatori\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert btnCrea != null : "fx:id=\"btnCrea\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'AddCenter.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'AddCenter.fxml'.";

	}
}
