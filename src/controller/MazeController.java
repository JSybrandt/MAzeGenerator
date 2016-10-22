package controller;

import Util.Pair;
import Util.Vec2;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.Maze;
import model.Room;
import model.SquareRoom;
import model.Wall;
import model.generator.EllerGenerator;
import model.generator.KruskalGenerator;
import model.generator.MazeGenerator;
import model.generator.PrimGenerator;
import view.LineData;
import view.MazePane;

import java.util.*;

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
        MazeGenerator gen = new EllerGenerator(maze);
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
