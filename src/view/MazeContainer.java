package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Created by jsybran on 10/22/2016.
 */
public class MazeContainer extends BorderPane{
    private Label title;
    private MazeCanvas mazeCanvas;
    private Button close;
    public MazeContainer(String title, MazeCanvas canvas){
        this.title = new Label(title);
        this.mazeCanvas = canvas;
        this.close = new Button("X");
        HBox header = new HBox();
        header.getChildren().add(this.title);
        header.getChildren().add(this.close);
        HBox.setHgrow(this.title, Priority.ALWAYS);
        HBox.setHgrow(this.close, Priority.NEVER);
        setTop(header);

        //Pane needed to enlarge the maze canvas
        Pane pane = new Pane();
        pane.getChildren().add(mazeCanvas);
        mazeCanvas.widthProperty().bind(pane.widthProperty());
        mazeCanvas.heightProperty().bind(pane.heightProperty());
        setCenter(pane);
        setPrefHeight(200);
        setPrefWidth(200);
    }

    public MazeCanvas getMazeCanvas(){return mazeCanvas;}
    public Button getClose(){return close;}

}
