package controller;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.HexMaze;
import model.Maze;
import model.SquareMaze;
import model.TileMaze;
import view.MazePane;

/**
 * Justin Sybrandt
 *
 * The application controller is responsible for initializing the views and controllers.
 * The application controller will also respond to user events passed back from the view.
 *
 * The application controller DOES NOT directly handle either the views or controllers.
 */
public class ApplicationController extends Controller{

    MazePane mazePane;
    Maze maze;
    MazeController mazeController;
    BorderPane root;

    public ApplicationController(BorderPane root){
        super(root);
        this.root = root;
    }

    @Override
    public void run(){
        createViews(root);
        createModels();
        attachControlers();
        runControllers();

    }

    private void createViews(BorderPane root){
        root.setTop(new Label("This is a test!"));
        mazePane = new MazePane(500,500);
        root.setCenter(mazePane);
    }

    private void createModels(){
        maze = new TileMaze(100,100);
    }

    private void attachControlers(){
        mazeController = new MazeController(mazePane,maze);
    }

    private void runControllers(){
        mazeController.run();
    }
}
