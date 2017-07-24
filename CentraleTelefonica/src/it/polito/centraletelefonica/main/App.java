package it.polito.centraletelefonica.main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class App extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) {

		App.stage = stage;

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("OverView.fxml"));
			BorderPane root = loader.load();
			Scene scene = new Scene(root, 1200, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			App.stage.setScene(scene);
			App.stage.centerOnScreen();
			App.stage.setTitle("Centrale Telefonica");
			App.stage.show();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}
}
