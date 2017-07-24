package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
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
	private LineChart<Number, Number> chart1;

	@FXML
	private PieChart chartType;

	@FXML
	private PieChart chartArea;

	@FXML
	void openRelativeAnalitycs(MouseEvent event) {

		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {

			Node node = (Node) event.getSource();
			String nodeID = node.getId();
			changeScene(nodeID);

		}

	}

	@FXML
	void openInNewTab(MouseEvent event) {

		if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {

			Node node = (Node) event.getSource();
			String nodeID = node.getId();
			showPopUpChart(node, nodeID);

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
		assert chartType != null : "fx:id=\"chartType\" was not injected: check your FXML file 'OverView.fxml'.";
		assert chartArea != null : "fx:id=\"chartArea\" was not injected: check your FXML file 'OverView.fxml'.";

		// Line chart

		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<Number, Number>(1, 23));
        series.getData().add(new XYChart.Data<Number, Number>(2, 14));
        series.getData().add(new XYChart.Data<Number, Number>(3, 15));
        series.getData().add(new XYChart.Data<Number, Number>(4, 24));
        series.getData().add(new XYChart.Data<Number, Number>(5, 34));
        series.getData().add(new XYChart.Data<Number, Number>(6, 36));
        series.getData().add(new XYChart.Data<Number, Number>(7, 22));
        series.getData().add(new XYChart.Data<Number, Number>(8, 45));
        series.getData().add(new XYChart.Data<Number, Number>(9, 43));
        series.getData().add(new XYChart.Data<Number, Number>(10, 17));
        series.getData().add(new XYChart.Data<Number, Number>(11, 29));
        series.getData().add(new XYChart.Data<Number, Number>(12, 25));
		series.setName("Open");
		chart1.getData().add(series);

		// add data to chartType

		chartType.getData().add(new Data("Prop 1", 25));
		chartType.getData().add(new Data("Prop 2", 25));
		chartType.getData().add(new Data("Prop 3", 25));
		chartType.getData().add(new Data("Prop 4", 25));

		// Add data to chartArea

		chartArea.getData().add(new Data("Prop 1", 25));
		chartArea.getData().add(new Data("Prop 1", 25));
		chartArea.getData().add(new Data("Prop 1", 25));
		chartArea.getData().add(new Data("Prop 1", 25));

	}
}
