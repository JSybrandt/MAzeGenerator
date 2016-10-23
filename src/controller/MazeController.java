package controller;

import Util.Pair;
import Util.Vec2;
import javafx.scene.shape.Polygon;
import model.Maze;
import model.Room;
import model.Wall;
import model.generator.EllerGenerator;
import model.generator.MazeGenerator;
import view.LineData;
import view.MazeCanvas;

import java.util.*;

/**
 * This controller is responsible for formatting the Maze model in a way that the MazeCanvas can display
 */
public class MazeController extends Controller {

    private MazeCanvas pane;
    private Maze maze;
    private MazeGenerator gen;

    MazeController(MazeCanvas pane, Maze maze, MazeGenerator gen){
        super(pane);
        this.pane = pane;
        this.maze = maze;
        this.gen = gen;
    }

    @Override
    public void run(){
        //Prep model
        Pair<Room> endPoints = gen.generate();
        pane.setStart(room2Poly(endPoints.getLeft().get()));
        pane.setEnd(room2Poly(endPoints.getRight().get()));
        Collection<Wall> walls = maze.getWalls();
        List<LineData> lines = new ArrayList<>();
        for(Wall w : walls){
            if(!w.isOpen)
                lines.add(new LineData(w.getLocations()));
        }

        /*
        Random rand = new Random();
        for(int i = 0; i < maze.getNumRows(); i++){
            Paint p = new Color(rand.nextDouble(),rand.nextDouble(),rand.nextDouble(),1);
            for(Room r : maze.getRow(i))
                pane.addDebug(room2Poly(r),p);
        }
        */

        //Send to view
        pane.setLines(lines);
        pane.drawMaze();
    }

    private Polygon room2Poly(Room r){
        Polygon poly = new Polygon();
        for(Vec2 corner : r.getCorners()){
            poly.getPoints().add(corner.X());
            poly.getPoints().add(corner.Y());
        }
        return poly;
    }
}
