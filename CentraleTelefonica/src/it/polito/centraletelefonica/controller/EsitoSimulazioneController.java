package it.polito.centraletelefonica.controller;

import it.polito.centraletelefonica.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class EsitoSimulazioneController {

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnChiudi;

	private Stage stage;

	@FXML
	void chiudiPop(ActionEvent event) {
		stage.hide();
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'EsitoSimulazioneView.fxml'.";
		assert btnChiudi != null : "fx:id=\"btnChiudi\" was not injected: check your FXML file 'EsitoSimulazioneView.fxml'.";
		txtResult.setText(Simulatore.esitoSimulazione);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		stage.setResizable(false);
	}

}
