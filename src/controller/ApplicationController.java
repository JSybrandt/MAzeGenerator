package controller;

import model.HexMaze;
import model.Maze;
import model.SquareMaze;
import model.TileMaze;
import model.generator.EllerGenerator;
import model.generator.KruskalGenerator;
import model.generator.MazeGenerator;
import model.generator.PrimGenerator;
import view.ApplicationPane;
import view.MazeCanvas;
import view.MazeContainer;
import view.SettingsPane;

import java.util.HashMap;
import java.util.Map;

/**
 * Justin Sybrandt
 *
 * The application controller is responsible for initializing the views and controllers.
 * The application controller will also respond to user events passed back from the view.
 *
 * The application controller DOES NOT directly handle either the views or controllers.
 */
public class ApplicationController extends Controller{


    MazeController mazeController;
    ApplicationPane appPane;
    Map<MazeController,MazeContainer> mazes;
    SettingsPane settingsPane;

    SettingsController settingsController;

    public ApplicationController(ApplicationPane root){
        super(root);
        this.appPane = root;
        mazes = new HashMap<>();
    }

    @Override
    public void run(){
        createViews();
        attachControlers();

    }

    private void createViews(){

        settingsPane = appPane.getSettingPane();
    }


    private void attachControlers(){
        settingsController = new SettingsController(settingsPane,this);
        settingsController.run();
    }

    public void addMaze(MazeDescription mazeDesc){

        Maze maze;
        switch (mazeDesc.getMazeOpt()){
            case SQUARE:
                maze = new SquareMaze(mazeDesc.getNumRows(),mazeDesc.getNumCols());
                break;
            case HEX:
                maze = new HexMaze(mazeDesc.getNumRows(),mazeDesc.getNumCols());
                break;
            case TILE:
                maze = new TileMaze(mazeDesc.getNumRows(),mazeDesc.getNumCols());
                break;
            default:
                maze = null;
        }

        MazeGenerator gen;
        switch (mazeDesc.getAlgOpt()){
            case PRIM:
                gen = new PrimGenerator(maze);
                break;
            case KRUSKAL:
                gen = new KruskalGenerator(maze);
                break;
            case ELLER:
                gen = new EllerGenerator(maze);
                break;
            default:
                gen = null;
                break;
        }

        MazeCanvas canvas = new MazeCanvas();
        MazeController controller = new MazeController(canvas,maze,gen);
        MazeContainer container = new MazeContainer(mazeDesc.toString(),canvas);
        appPane.addMazeContainer(container);
        mazes.put(controller,container);
        container.getClose().setOnAction(evt -> appPane.removeMazeContainer(container));
        controller.run();
    }
}
