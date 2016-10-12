package view;

import Util.Pair;
import Util.Vec2;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javax.naming.Context;
import java.awt.*;
import java.util.List;

/**
 * The maze panel draws a set of lines. Intended to be used with the Maze Controller.
 */
public class MazePane extends Canvas {

    private List<LineData> lines;
    private GraphicsContext context;

    public MazePane(double width, double height){
        super(width,height);
        context = this.getGraphicsContext2D();
    }

    public void setLines(List<LineData> lines){this.lines = lines;}

    public void drawMaze(){
        context.clearRect(0,0,this.getWidth(),this.getHeight());
        for (LineData line : lines){
            if(line.getPoints().bothPresent()) {
                context.setStroke(line.getColor());
                context.setLineWidth(line.getThickness());
                Vec2 p1 = line.getPoints().getLeft().get();
                Vec2 p2 = line.getPoints().getRight().get();
                context.strokeLine(p1.X(),p1.Y(),p2.X(),p2.Y());
            }
        }
    }

}
