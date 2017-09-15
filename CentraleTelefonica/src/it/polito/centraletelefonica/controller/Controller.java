package it.polito.centraletelefonica.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.centraletelefonica.main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

abstract class Controller {

	// It maps the bnt id to the relative fxml file
	private Map<String, String> idFx;
	// Same for css file
	private Map<String, String> idCss;
	// map for scenes (key = nodeId)
	private Map<String, Scene> scenes;

	@SuppressWarnings("unchecked")
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

				for (Data data : pie.getData()) {
					String name = data.getName();
					double value = data.getPieValue();
					Data d = new Data(name, value);
					pie1.getData().add(d);
				}

				String title = pie.getTitle();
				pie1.setTitle(title);
				root.setCenter(pie1);
			}

			if (node instanceof LineChart<?, ?>) {
				NumberAxis xAxis = new NumberAxis();
				NumberAxis yAxis = new NumberAxis();
				LineChart<Number, Number> line1 = new LineChart<>(xAxis, yAxis);
				LineChart<Number, Number> line2 = new LineChart<>(xAxis, yAxis);
				XYChart.Series<Number, Number> clonedSeries = new XYChart.Series<>();
				line1 = (LineChart<Number, Number>) node;
				List<Series<Number, Number>> data = new LinkedList<>(line1.getData());

				for (Series<Number, Number> series : data) {
					for (javafx.scene.chart.XYChart.Data<Number, Number> dat : series.getData()) {
						clonedSeries.getData().add(dat);
					}

				}

				String title = line1.getTitle();
				line2.setTitle(title);
				clonedSeries.setName(line1.getData().get(0).getName());
				line2.getData().add(clonedSeries);
				root.setCenter(line2);

			}

			stage.setScene(scene);
			stage.setWidth(500);
			stage.setHeight(500);
			stage.show();

		}

		return false;
	}

	boolean showSearchTool() {

		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(App.class.getResource("Search.fxml"));
		BorderPane root;
		Scene scene = null;
		try {
			root = loader.load();
			scene = new Scene(root);
			scene.getStylesheets().add(App.class.getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private Scene createScene(String nodeID) {

		if (!getScenes().containsKey(nodeID)) {

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
						getScenes().put(nodeID, scene);
						return scene;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return getScenes().get(nodeID);

	}

	private Map<String, Scene> getScenes() {

		if (scenes == null) {
			scenes = new LinkedHashMap<String, Scene>();
		}

		return scenes;
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
			idFx.put("btnPaths", "PathsView.fxml");

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
			idCss.put("btnPaths", "application.css");

		}

		return idCss;
	}

}
