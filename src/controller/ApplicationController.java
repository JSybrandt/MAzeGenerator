/*
* Justin Sybrandt
*
* Description:
* The application controller is responsible for initializing the views and controllers.
* This meta controller also adds mazes to the application pane when given a maze description.
*
* Important Values:
* appPane - the main view.
* mazeController2View - stores a collection of maze controllers and views.
*                       The map structure allows for easy storage of pairs.
*
*
* */


package controller;

import model.maze.HexMaze;
import model.maze.Maze;
import model.maze.SquareMaze;
import model.maze.TileMaze;
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

public class ApplicationController extends Controller{

    ApplicationPane appPane;
    Map<MazeController,MazeContainer> mazeController2View;

    SettingsPane settingsPane;
    SettingsController settingsController;

    public ApplicationController(ApplicationPane root){
        super(root);
        this.appPane = root;
        mazeController2View = new HashMap<>();
    }

    @Override
    public void run(){
        createViews();
        attachControllers();

    }

    private void createViews(){

        settingsPane = appPane.getSettingPane();
    }

    private void attachControllers(){
        settingsController = new SettingsController(settingsPane,this);
        settingsController.run();
    }

    //called by other controllers
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
        mazeController2View.put(controller,container);

        //remove controllers when the pane closes.
        container.getCloseButton().setOnAction(evt -> appPane.removeMazeContainer(container));
        controller.run();
    }
}
