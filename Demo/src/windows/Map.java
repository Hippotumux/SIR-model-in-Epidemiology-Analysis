package windows;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Map extends Application {
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("1000x1000 Window");
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 600);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}