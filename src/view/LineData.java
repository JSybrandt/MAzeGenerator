package view;

import Util.Pair;
import Util.Vec2;
import javafx.scene.paint.Color;

/**
 * The line data class holds information for lines in screenspace. This includes color and thickness.
 */
public class LineData {
    private Pair<Vec2> points;
    private javafx.scene.paint.Color color;
    private double thickness;

    static final Color DEFAULT_COLOR = Color.BLACK;

    static final double DEFAULT_THICKNESS = 3;

    public LineData(Pair<Vec2> points){
        this(points, DEFAULT_COLOR, DEFAULT_THICKNESS);
    }

    public LineData(Pair<Vec2> points, Color color, double thickness){
        this.points = points;
        this.color = color;
        this.thickness = thickness;
    }

    public Pair<Vec2> getPoints() {return points;}
    public Color getColor() {return color;}
    public double getThickness() {return thickness;}
}
