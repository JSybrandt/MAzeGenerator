/*
* Justin Sybrandt
*
* Description:
* The MazeCanvas draws a set of lines, stored as LineData. When drawing these lines, the canvas scales each
* line based on its current width and height.
* Used with the MazeController
*
* Important Values:
* lines - the collection of lines to draw
* context - the 2d context object which can draw shapes.
* fillPoly - displays a Polygon object with a given color.
*
* */

package view;

import util.Vec2;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public class MazeCanvas extends Canvas {

    private List<LineData> lines;
    private GraphicsContext context;

    private Polygon start = null;
    private Polygon end = null;

    private Paint startColor = Color.GREEN;
    private Paint endColor = Color.RED;

    public MazeCanvas(){
        super();
        lines = new ArrayList<>();
        context = this.getGraphicsContext2D();
        maxHeight(Double.MAX_VALUE);
        maxWidth(Double.MAX_VALUE);
        widthProperty().addListener(observable -> drawMaze());
        heightProperty().addListener(observable -> drawMaze());
    }

    public void setLines(List<LineData> lines){this.lines = lines;}

    public void setStart(Polygon poly){
        start = poly;
    }

    public void setEnd(Polygon poly){
        end = poly;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    public void drawMaze(){

        context.clearRect(0,0,this.getWidth(),this.getHeight());

        double scale = Double.min(this.getHeight(),this.getWidth());

        if(start != null){
            fillPoly(start,startColor, scale);
        }
        if(end != null){
            fillPoly(end,endColor, scale);
        }

        for (LineData line : lines){
            if(line.getPoints().bothPresent()) {
                context.setStroke(line.getColor());
                context.setLineWidth(line.getThickness());
                Vec2 leftPoint = line.getPoints().getLeft().get().scale(scale);
                Vec2 rightPoint = line.getPoints().getRight().get().scale(scale);
                context.strokeLine(leftPoint.X(),leftPoint.Y(),rightPoint.X(),rightPoint.Y());
            }
        }
    }

    private void fillPoly(Polygon poly, Paint paint, double scale){
        context.setFill(paint);
        int size = poly.getPoints().size();
        double[] x = new double[size/2];
        double[] y = new double[size/2];
        for(int pointIndex = 0 ; pointIndex < size; pointIndex++){
            if(pointIndex % 2 == 0) {
                x[pointIndex/2] = poly.getPoints().get(pointIndex) * scale;
            } else{
                y[pointIndex/2] = poly.getPoints().get(pointIndex) * scale;
            }
        }
        context.fillPolygon(x,y,size/2);
    }

}
