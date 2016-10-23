/**
 * Justin Sybrandt
 * This is the main class for the Maze Generator
 *
 * The main class is responsible for starting the javafx application
 * (this just creates the window)
 * and the main class also needs to spawn the main controller.
 *
 * Note: JavaFX requires that the main application define a root pane, although this slightly
 * contradicts a string MVC paradigm. If it were me, the ApplicationController would created the
 * ApplicationPane.
 */

import controller.ApplicationController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ApplicationPane;

public class Main extends Application{

    @Override
    public void start(Stage stage){
        ApplicationPane root = new ApplicationPane();
        Scene scene = new Scene(root);
        stage.setTitle("Maze Generator");
        stage.setHeight(300);
        stage.setWidth(300);
        stage.setScene(scene);
        stage.setFullScreen(true);

        ApplicationController controller = new ApplicationController(root);
        controller.run();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
