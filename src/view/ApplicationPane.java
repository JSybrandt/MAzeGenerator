package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by jsybran on 10/22/2016.
 */
public class ApplicationPane extends BorderPane {

    private SettingsPane settingPane;
    private ScrollPane scrollPane;
    private HBox mazes;
    public ApplicationPane(){
        settingPane = new SettingsPane();
        scrollPane = new ScrollPane();
        mazes = new HBox();
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(mazes);
        setTop(settingPane);
        setCenter(scrollPane);
    }

    public SettingsPane getSettingPane(){return settingPane;}

    public void addMazeContainer(MazeContainer container){
        mazes.getChildren().add(container);
    }

}
