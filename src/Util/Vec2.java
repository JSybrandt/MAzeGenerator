package Util;

import java.util.Objects;

/**
 * Justin Sybrandt
 *
 * This is a pretty standard vec2 which is used to place points in MazeSpace
 */
public class Vec2 {
    public static double eps = 0.001;
    double x, y;
    public Vec2(){
        x = y = 0;
    }
    public Vec2(double X, double Y){
        x=X;
        y=Y;
    }
    public double X(){return x;}
    public double Y(){return y;}

    public Vec2 plus(Vec2 other){
        return new Vec2(x+other.x,y+other.y);
    }

    public Vec2 minus(Vec2 other){
        return new Vec2(x-other.x,y-other.y);
    }

    public Vec2 rotate(double rads){
        return new Vec2(
                        x*Math.cos(rads)-y* Math.sin(rads),
                        x*Math.sin(rads)+y*Math.cos(rads)
                        );
    }

    public Vec2 scale(double scale){
        return new Vec2(x*scale, y*scale);
    }

    public double magnitude(){
        return Math.sqrt(x*x+y*y);
    }
    public double dot(Vec2 other){return x*other.x + y*other.y;}
    public Vec2 unit(){
        return scale(1/magnitude());
    }

    @Override
    public String toString(){return "("+x+","+y+")";}

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Vec2) {
            Vec2 o = (Vec2) other;
            return (Math.abs(x - o.x) < eps) && (Math.abs(y - o.y) < eps);
        }
        else
            return false;
    }

    public double angle(Vec2 other){
        return Math.acos(this.dot(other) / (this.magnitude() * other.magnitude()));
    }

}
