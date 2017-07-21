package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class OverViewController extends Controller {

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
	private Label lblFirstPercentage;

	@FXML
	private Label lblSecondPercentage;

	@FXML
	private Label lblThirdPercentage;

	@FXML
	private Label lblFourthPercentage;

	@FXML
	private LineChart<?, ?> chart1;

	@FXML
	private LineChart<?, ?> chart2;

	@FXML
	private LineChart<?, ?> chart3;

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

		// Default implementation

		assert btnOverview != null : "fx:id=\"btnOverview\" was not injected: check your FXML file 'OverView.fxml'.";
		assert btnOperations != null : "fx:id=\"btnOperations\" was not injected: check your FXML file 'OverView.fxml'.";
		assert btnOperationsCenter != null : "fx:id=\"btnOperationsCenter\" was not injected: check your FXML file 'OverView.fxml'.";
		assert btnWorkers != null : "fx:id=\"btnWorkers\" was not injected: check your FXML file 'OverView.fxml'.";
		assert dateFrom != null : "fx:id=\"dateFrom\" was not injected: check your FXML file 'OverView.fxml'.";
		assert dateTo != null : "fx:id=\"dateTo\" was not injected: check your FXML file 'OverView.fxml'.";
		assert lblFirstPercentage != null : "fx:id=\"lblFirstPercentage\" was not injected: check your FXML file 'OverView.fxml'.";
		assert lblSecondPercentage != null : "fx:id=\"lblSecondPercentage\" was not injected: check your FXML file 'OverView.fxml'.";
		assert lblThirdPercentage != null : "fx:id=\"lblThirdPercentage\" was not injected: check your FXML file 'OverView.fxml'.";
		assert lblFourthPercentage != null : "fx:id=\"lblFourthPercentage\" was not injected: check your FXML file 'OverView.fxml'.";
		assert chart1 != null : "fx:id=\"chart1\" was not injected: check your FXML file 'OverView.fxml'.";
		assert chart2 != null : "fx:id=\"chart2\" was not injected: check your FXML file 'OverView.fxml'.";
		assert chart3 != null : "fx:id=\"chart3\" was not injected: check your FXML file 'OverView.fxml'.";

	}
}
