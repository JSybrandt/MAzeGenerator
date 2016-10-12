/**
 * Justin Sybrandt
 * This is the main class for the Maze Generator
 *
 * The main class is responsible for starting the javafx application
 * (this just creates the window)
 * and the main class also needs to spawn the main controller.
 */

import javafx.application.Application;
import controller.ApplicationController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        stage.setTitle("Maze Generator");
        stage.setHeight(300);
        stage.setWidth(300);
        stage.setScene(scene);
        //stage.setFullScreen(true);
        //stage.setMaximized(true);

        ApplicationController controller = new ApplicationController(root);
        controller.run();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
