package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Justin Sybrandt
 *
 * The room class defines a single element in a Maze.
 * A room has some number of walls, each wall is shared with another room.
 * Walls are removed to create a maze.
 */
public class Room {

    public enum RoomType{Triangle, Square, Hexagon};

    public static final double DEFAULT_ROTATION = 0;
    public static double DEFAULT_WALL_LENGTH = 1;

    private int numWalls;
    private RoomType roomType;
    private Vec2 location;
    //Note: Corners are relative to location
    private List<Vec2> corners;
    private List<Wall> walls;

    private class Wall{
        public Vec2 loc1, loc2;
        public Room other;
        public Wall(Vec2 loc1, Vec2 loc2, Room other){
            this.loc1 = loc1;
            this.loc2 = loc2;
            this.other = other;
        }
    }

    public Room(RoomType roomType, Vec2 location){
        this(roomType,location,DEFAULT_ROTATION,DEFAULT_WALL_LENGTH);
    }
    public Room(RoomType roomType, Vec2 location, double rotation){this(roomType,location,rotation,DEFAULT_WALL_LENGTH);}
    public Room(RoomType roomType, Vec2 location, double rotation, double wallLength){
        this.roomType = roomType;
        this.location = location;
        switch (roomType){
            case Triangle:
                numWalls = 3;
                break;
            case Square:
                numWalls = 4;
                break;
            case Hexagon:
                numWalls = 6;
                break;
        }
        double angle = 2*Math.PI/numWalls;
        corners = new ArrayList<Vec2>();
        //The strat is to go in a circle building walls starting at 0,0 then translate by the center.
        corners.add(new Vec2(0,0));
        Vec2 wallVec = new Vec2(wallLength,0);
        Vec2 avgPos = new Vec2(0,0);
        for(int i=0; i<numWalls-1;i++){
            Vec2 newCorner = corners.get(i).plus(wallVec.rotate(angle * i));
            corners.add(newCorner);
            avgPos = avgPos.plus(newCorner);
        }
        System.out.println("Pre Trans");
        for(Vec2 v : corners)
            System.out.println(v);
        avgPos = avgPos.scale(1.0/numWalls);
        //now we need to center the corners around (0,0) and rotate them.
        for(int i=0;i<corners.size();i++){
            corners.set(i,corners.get(i).minus(avgPos).rotate(rotation));
        }
        System.out.println("Post Trans");
        for(Vec2 v : corners)
            System.out.println(v);
   }

   public List<Vec2>getCorners(){
       ArrayList<Vec2> ret = new ArrayList<Vec2>();
       for (Vec2 vec:corners) {
           ret.add(vec.plus(location));
       }
       System.out.println("Screen");
       for(Vec2 vec : ret)
           System.out.println(vec);
       return ret;
   }
   public int getNumWalls(){return numWalls;}
}
