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

        Collection<Wall> walls = maze.getWalls();
        List<LineData> lines = new ArrayList<>();
        double maxX = 0;
        double maxY = 0;
        for(Wall w : walls){
            for(Vec2 v : w.getLocations().toList()){
                maxX = Double.max(maxX,v.X());
                maxY = Double.max(maxY,v.Y());
            }
        }
        double scale = 1.0 / Double.max(maxX,maxY);
        for(Wall w : walls){
            if(!w.isOpen) {
                Vec2 left = w.getLocations().getLeft().get();
                Vec2 right = w.getLocations().getRight().get();
                left = left.scale(scale);
                right = right.scale(scale);
                lines.add(new LineData(new Pair<Vec2>(left,right)));
            }

        }

        pane.setStart(room2Poly(endPoints.getLeft().get(),scale));
        pane.setEnd(room2Poly(endPoints.getRight().get(),scale));

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

    private Polygon room2Poly(Room r, double scale){
        Polygon poly = new Polygon();
        for(Vec2 corner : r.getCorners()){
            Vec2 pt = corner.scale(scale);
            poly.getPoints().add(pt.X());
            poly.getPoints().add(pt.Y());
        }
        return poly;
    }
}
