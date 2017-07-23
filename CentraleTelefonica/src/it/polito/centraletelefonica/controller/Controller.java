package it.polito.centraletelefonica.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.centraletelefonica.main.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

abstract class Controller {

	// It maps the bnt id to the relative fxml file
	private Map<String, String> idFx;
	// Same for css file
	private Map<String, String> idCss;

	boolean showPopUpChart(Node node, String nodeId) {

		Stage stage = new Stage();
		Scene scene = createScene(nodeId);

		if (scene != null) {

			double mainX = App.getStage().getX();
			double mainY = App.getStage().getY();
			double popX = mainX + 600;
			double popY = mainY - 50;
			stage.setX(popX);
			stage.setY(popY);
			BorderPane root = (BorderPane) scene.getRoot();

			if (node instanceof PieChart) {
				PieChart pie = new PieChart();
				PieChart pie1 = new PieChart();
				pie = (PieChart) node;
				pie1.getData().addAll(pie.getData());
				root.setCenter(pie1);
			}

			if (node instanceof LineChart<?, ?>) {
				LineChart<?, ?> lineChart = new LineChart<>(null, null);
				lineChart = (LineChart<?, ?>) node;
				root.setCenter(lineChart);

			}

			stage.setScene(scene);
			stage.setWidth(500);
			stage.setHeight(500);
			stage.show();

		}

		return false;
	}

	private Scene createScene(String nodeID) {

		if (getIdFxMap().containsKey(nodeID)) {
			String fileName = getIdFxMap().get(nodeID);
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fileName));
			BorderPane root;
			Scene scene = null;
			try {
				root = loader.load();
				double previousWidth = App.getStage().getScene().getWidth();
				double previousHeight = App.getStage().getScene().getHeight();
				scene = new Scene(root, previousWidth, previousHeight);
				if (getIdCss().containsKey(nodeID)) {
					String cssFileName = getIdCss().get(nodeID);
					scene.getStylesheets().add(App.class.getResource(cssFileName).toExternalForm());
					return scene;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	boolean changeScene(String nodeID) {

		Scene scene = createScene(nodeID);

		if (scene != null) {

			App.getStage().setScene(scene);
			return true;

		}

		return false;

	}

	private Map<String, String> getIdFxMap() {

		if (idFx == null) {

			idFx = new LinkedHashMap<>();
			idFx.put("btnOverview", "Overview.fxml");
			idFx.put("btnOperations", "OperationsView.fxml");
			idFx.put("btnOperationsCenter", "OperationCenterView.fxml");
			idFx.put("btnWorkers", "WorkersView.fxml");
			idFx.put("chart1", "PopUpChart.fxml");
			idFx.put("chartType", "PopUpChart.fxml");
			idFx.put("chartArea", "PopUpChart.fxml");

		}

		return idFx;
	}

	private Map<String, String> getIdCss() {

		if (idCss == null) {

			idCss = new LinkedHashMap<>();
			idCss.put("btnOverview", "application.css");
			idCss.put("btnOperations", "application.css");
			idCss.put("btnOperationsCenter", "application.css");
			idCss.put("btnWorkers", "application.css");
			idCss.put("chart1", "application.css");
			idCss.put("chartType", "application.css");
			idCss.put("chartArea", "application.css");

		}

		return idCss;
	}

}
