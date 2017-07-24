package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class SearchController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnClose;

	@FXML
	private TextField txtSearch;

	@FXML
	void close(ActionEvent event) {

	}

	@FXML
	void search(KeyEvent event) {

	}

	@FXML
	void initialize() {
		assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'Search.fxml'.";
		assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'Search.fxml'.";
	}
}
