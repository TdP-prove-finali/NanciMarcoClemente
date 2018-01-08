package it.polito.centraletelefonica.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.polito.centraletelefonica.model.ChiusureRow;
import it.polito.centraletelefonica.model.Model;
import it.polito.centraletelefonica.model.NuoveRow;
import it.polito.centraletelefonica.model.TabRow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Button btnPaths;

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
	private LineChart<LocalDate, Number> chart1;

	@FXML
	private PieChart chartType;

	@FXML
	private PieChart chartArea;

	@FXML
	private Label lblAmountOpen;

	@FXML
	private Label lblAmountClosed;

	@FXML
	private ComboBox<String> boxPeriodo;

	@FXML
	private TableView<ChiusureRow> tabChiusure;

	@FXML
	private TableColumn<TabRow, String> colPeriodoChiusure;

	@FXML
	private TableColumn<TabRow, String> colPeriodo;

	@FXML
	private TableColumn<ChiusureRow, Integer> colOp;

	@FXML
	private TableColumn<ChiusureRow, Integer> colOpConcluse;

	@FXML
	private TableColumn<TabRow, Double> colMediaPeriodo;

	@FXML
	private TableColumn<TabRow, Double> colMediaConcluse;

	@FXML
	private TableColumn<TabRow, Double> colDiffPunti;

	@FXML
	private TableColumn<TabRow, Double> colDiffPuntiConcluse;

	@FXML
	private TableView<NuoveRow> tabNuove;

	@FXML
	private TableColumn<NuoveRow, Integer> colNuove;

	@FXML
	private TableView<?> tabCarico;

	@FXML
	private Button btnStatistiche;

	@FXML
	void calcolaStatistiche(ActionEvent event) {

		LocalDate from = dateFrom.getValue();
		LocalDate to = dateTo.getValue();
		String periodoSelezionato = boxPeriodo.getValue();

		System.out.println(model);

		// Effettuo tra i giorni selezionati(grafici)
		if (from != null && to != null) {
			if (from.compareTo(to) <= 0) {
				if (from.compareTo(LocalDate.now()) <= 0 && to.compareTo(LocalDate.now()) <= 0) {
					model.getAreaPercentage(from, to, chartArea.getData());
					model.getTypePercentage(from, to, chartType.getData());
				} else
					showAlert("niente previsioni per il futuro!");

			} else
				showAlert("la data di partenza deve essere precedente alla data d'arrivo");

		}

		// Effettuo analisi sui periodi(tabelle)
		else if (periodoSelezionato.compareTo("Seleziona periodo") != 0) {
			tabChiusure.setItems(model.getChiusure(periodoSelezionato));
			tabNuove.setItems(model.getNuove(periodoSelezionato));
		}

		else
			showAlert("devi selezionare un periodo");

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
		assert chartType != null : "fx:id=\"chartType\" was not injected: check your FXML file 'OverView.fxml'.";
		assert chartArea != null : "fx:id=\"chartArea\" was not injected: check your FXML file 'OverView.fxml'.";
		assert btnPaths != null : "fx:id=\"btnPaths\" was not injected: check your FXML file 'OverView.fxml'.";
		assert lblAmountOpen != null : "fx:id=\"lblAmountOpen\" was not injected: check your FXML file 'OverView.fxml'.";
		assert lblAmountClosed != null : "fx:id=\"lblAmountClosed\" was not injected: check your FXML file 'OverView.fxml'.";
		assert boxPeriodo != null : "fx:id=\"boxPeriodo\" was not injected: check your FXML file 'OverView.fxml'.";
		assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'OverView.fxml'.";
		assert tabChiusure != null : "fx:id=\"tabChiusure\" was not injected: check your FXML file 'OverView.fxml'.";
		assert tabNuove != null : "fx:id=\"tabNuove\" was not injected: check your FXML file 'OverView.fxml'.";
		assert tabCarico != null : "fx:id=\"tabCarico\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colPeriodo != null : "fx:id=\"colPeriodo\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colOpConcluse != null : "fx:id=\"colOpConcluse\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colMediaPeriodo != null : "fx:id=\"colMediaPeriodo\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colDiffPunti != null : "fx:id=\"colDiffPunti\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colNuove != null : "fx:id=\"colNuove\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colPeriodoChiusure != null : "fx:id=\"colPeriodoChiusure\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colMediaConcluse != null : "fx:id=\"colMediaConcluse\" was not injected: check your FXML file 'OverView.fxml'.";
		assert colDiffPuntiConcluse != null : "fx:id=\"colDiffPuntiConcluse\" was not injected: check your FXML file 'OverView.fxml'.";

		colPeriodo.setCellValueFactory(new PropertyValueFactory<TabRow, String>("periodo"));
		colPeriodoChiusure.setCellValueFactory(new PropertyValueFactory<TabRow, String>("periodo"));
		colOpConcluse.setCellValueFactory(new PropertyValueFactory<ChiusureRow, Integer>("opConcluse"));
		colMediaPeriodo.setCellValueFactory(new PropertyValueFactory<TabRow, Double>("mediaPeriodo"));
		colMediaConcluse.setCellValueFactory(new PropertyValueFactory<TabRow, Double>("mediaPeriodo"));
		colDiffPunti.setCellValueFactory(new PropertyValueFactory<TabRow, Double>("diffPunti"));
		colDiffPuntiConcluse.setCellValueFactory(new PropertyValueFactory<TabRow, Double>("diffPunti"));
		colNuove.setCellValueFactory(new PropertyValueFactory<NuoveRow, Integer>("nuoveSegnalazioni"));

		// Box section
		boxPeriodo.getItems().addAll("Seleziona periodo", "Mese", "Trimestre", "Quadrimestre", "Semestre");
		boxPeriodo.setValue("Seleziona periodo");

		// add data to chartType

		chartType.getData().addAll(Model.initPieType());

		// Add data to chartArea

		chartArea.getData().addAll(Model.initPieArea());

	}

}
