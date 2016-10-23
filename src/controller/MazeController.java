/*
* Justin Sybrandt
*
* Description:
* This controller is responsible for formatting the Maze model in a way that the MazeCanvas can display.
* The controller is dependent on all maze components, consisting of a Maze, a MazeGenerator, and a MazeCanvas.
*
* This controller's main contribution is scaling the maze from maze-space to values between 0 and 1.
* This scaled version will later be used by the view to scale the maze to whatever size the view happens to be.
*
* Important Values:
* canvas
* maze
* generator
*
* */

package controller;

import Util.Pair;
import Util.Vec2;
import javafx.scene.shape.Polygon;
import model.maze.Maze;
import model.room.Room;
import model.room.Wall;
import model.generator.MazeGenerator;
import view.LineData;
import view.MazeCanvas;

import java.util.*;

public class MazeController extends Controller {

    private MazeCanvas canvas;
    private Maze maze;
    private MazeGenerator generator;

    MazeController(MazeCanvas canvas, Maze maze, MazeGenerator generator){
        super(canvas);
        this.canvas = canvas;
        this.maze = maze;
        this.generator = generator;
    }

    @Override
    public void run(){
        //Prep model
        Pair<Room> endPoints = generator.generate();

        Collection<Wall> walls = maze.getWalls();
        List<LineData> lines = new ArrayList<>();
        double maxVal = 0;
        for(Wall wall : walls){
            for(Vec2 v : wall.getLocations().toList()){
                maxVal = Double.max(maxVal,v.X());
                maxVal = Double.max(maxVal,v.Y());
            }
        }
        double scale = 1.0 / maxVal;
        for(Wall wall : walls){
            if(!wall.isOpen) {
                Vec2 left = wall.getLocations().getLeft().get();
                Vec2 right = wall.getLocations().getRight().get();
                left = left.scale(scale);
                right = right.scale(scale);
                lines.add(new LineData(new Pair<Vec2>(left,right)));
            }

        }

        canvas.setStart(room2Poly(endPoints.getLeft().get(),scale));
        canvas.setEnd(room2Poly(endPoints.getRight().get(),scale));

        //Send to view
        canvas.setLines(lines);
        canvas.drawMaze();
    }

    private Polygon room2Poly(Room room, double scale){
        Polygon poly = new Polygon();
        for(Vec2 corner : room.getCorners()){
            Vec2 pt = corner.scale(scale);
            poly.getPoints().add(pt.X());
            poly.getPoints().add(pt.Y());
        }
        return poly;
    }
}
