package view;

import Util.Pair;
import Util.Vec2;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import javax.naming.Context;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The maze panel draws a set of lines. Intended to be used with the Maze Controller.
 */
public class MazePane extends Canvas {

    private List<LineData> lines;
    private GraphicsContext context;

    private Polygon start = null;
    private Polygon end = null;

    private Paint startColor = Color.GREEN;
    private Paint endColor = Color.RED;

    private Map<Polygon, Paint> debugColors;

    public MazePane(double width, double height){
        super(width,height);
        context = this.getGraphicsContext2D();
        debugColors = new HashMap<>();
    }

    public void addDebug(Polygon poly, Paint paint){
        debugColors.put(poly,paint);
    }

    public void setLines(List<LineData> lines){this.lines = lines;}

    public void setStart(Polygon poly){
        start = poly;
    }

    public void setEnd(Polygon poly){
        end = poly;
    }




    public void drawMaze(){

        context.clearRect(0,0,this.getWidth(),this.getHeight());

        if(start != null){
            fillPoly(start,startColor);
        }
        if(end != null){
            fillPoly(end,endColor);
        }

        for(Polygon polygon : debugColors.keySet()){
            fillPoly(polygon,debugColors.get(polygon));
        }

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

    private void fillPoly(Polygon poly, Paint paint){
        context.setFill(paint);
        int size = poly.getPoints().size();
        double[] x = new double[size/2];
        double[] y = new double[size/2];
        for(int i = 0 ; i < size; i++){
            if(i % 2 == 0) {
                x[i/2] = poly.getPoints().get(i);
            } else{
                y[i/2] = poly.getPoints().get(i);
            }
        }
        context.fillPolygon(x,y,size/2);
    }

}
