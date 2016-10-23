/*
* Justin Sybrandt
*
* Description:
* This is the main pane. At the top is the SettingsPane, in the center is a collection of MazeContainers.
* Controlled by the Application Controller.
*
* Important Values:
* mazeContainerTilePane - stores a collection of MazeContainers and displays them based on space available.
*
* */

package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

public class ApplicationPane extends BorderPane {

    private SettingsPane settingPane;
    private ScrollPane scrollPane;
    private TilePane mazeContainerTilePane;
    public ApplicationPane(){
        settingPane = new SettingsPane();
        scrollPane = new ScrollPane();
        mazeContainerTilePane = new TilePane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mazeContainerTilePane);
        setTop(settingPane);
        setCenter(scrollPane);
    }

    public SettingsPane getSettingPane(){return settingPane;}

    public void addMazeContainer(MazeContainer container){
        mazeContainerTilePane.getChildren().add(container);
    }

    public void removeMazeContainer(MazeContainer container){
        mazeContainerTilePane.getChildren().remove(container);
    }

}
