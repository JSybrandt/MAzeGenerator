package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class ApplicationPane extends BorderPane {

    private SettingsPane settingPane;
    private ScrollPane scrollPane;
    private TilePane mazes;
    public ApplicationPane(){
        settingPane = new SettingsPane();
        scrollPane = new ScrollPane();
        mazes = new TilePane();
        //scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mazes);
        setTop(settingPane);
        setCenter(scrollPane);
    }

    public SettingsPane getSettingPane(){return settingPane;}

    public void addMazeContainer(MazeContainer container){
        mazes.getChildren().add(container);
    }

    public void removeMazeContainer(MazeContainer container){
        mazes.getChildren().remove(container);
    }

}
