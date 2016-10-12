package controller;

import javafx.scene.input.MouseEvent;
import model.Maze;
import model.SquareRoom;
import model.Wall;
import view.LineData;
import view.MazePane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This controller is responsible for formatting the Maze model in a way that the MazePane can display
 */
public class MazeController extends Controller {

    private MazePane pane;
    private Maze maze;

    MazeController(MazePane pane, Maze maze){
        super(pane);
        this.pane = pane;
        this.maze = maze;
    }

    @Override
    public void run(){

        //Prep model
        maze.generateMaze();
        Collection<Wall> walls = maze.getWalls();
        List<LineData> lines = new ArrayList<>();
        for(Wall w : walls){
            lines.add(new LineData(w.getLocations()));
        }

        //Send to view
        pane.setLines(lines);
        pane.drawMaze();
    }
}
