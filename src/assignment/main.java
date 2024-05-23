package assignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class main extends Application {

	@Override
	public void start(Stage primaryStage) {

		BorderPane borderPane = new BorderPane();
		borderPane.autosize();

		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 400, 400);
		primaryStage.setTitle("Home");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
