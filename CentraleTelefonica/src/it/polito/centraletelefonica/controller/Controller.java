package it.polito.centraletelefonica.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import it.polito.centraletelefonica.main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

abstract class Controller {

	// It maps the bnt id to the relative fxml file
	private Map<String, String> idFx;
	private Map<String, String> idCss;

	boolean changeScene(String nodeID) {

		if (getIdFxMap().containsKey(nodeID)) {

			String fileName = getIdFxMap().get(nodeID);
			FXMLLoader loader = new FXMLLoader(App.class.getResource(fileName));
			BorderPane root;

			try {

				root = loader.load();
				double previousWidth = App.getStage().getScene().getWidth();
				double previousHeight = App.getStage().getScene().getHeight();
				Scene scene = new Scene(root,previousWidth,previousHeight);

				if (getIdCss().containsKey(nodeID)) {

					String cssFileName = getIdCss().get(nodeID);
					scene.getStylesheets().add(App.class.getResource(cssFileName).toExternalForm());
				}

				App.getStage().setScene(scene);
				return true;

			}

			catch (IOException e) {
				e.printStackTrace();
			}

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

		}

		return idCss;
	}

}
