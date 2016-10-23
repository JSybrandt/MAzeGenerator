package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MazeContainer extends BorderPane{

    private static double DEFAULT_SIZE = 400;

    private Label title;
    private MazeCanvas mazeCanvas;
    private Button close;
    public MazeContainer(String title, MazeCanvas canvas){
        this.title = new Label(title);
        this.mazeCanvas = canvas;
        this.close = new Button("X");
        BorderPane header = new BorderPane();
        header.setLeft(this.title);
        header.setRight(this.close);
        HBox.setHgrow(this.title, Priority.ALWAYS);
        HBox.setHgrow(this.close, Priority.NEVER);
        HBox.setHgrow(header, Priority.ALWAYS);
        setTop(header);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //Pane needed to enlarge the maze canvas
        Pane pane = new Pane();
        pane.getChildren().add(mazeCanvas);
        mazeCanvas.widthProperty().bind(pane.widthProperty());
        mazeCanvas.heightProperty().bind(pane.heightProperty());
        setCenter(pane);
        setPrefHeight(DEFAULT_SIZE);
        setPrefWidth(DEFAULT_SIZE);
    }

    public MazeCanvas getMazeCanvas(){return mazeCanvas;}
    public Button getClose(){return close;}

}
