/*
* Justin Sybrandt
*
* Description:
* The line data class holds information for lines with values between 0 and 1. This includes color and thickness.
* This is used by the MazeCanvas to display lines.
*
* Important Values:
* points - set of two points in screenspace
* thickness - default to 3 pixels
*
*
* */

package view;

import util.Pair;
import util.Vec2;
import javafx.scene.paint.Color;

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
