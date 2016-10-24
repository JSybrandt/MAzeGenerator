/*
* Justin Sybrandt
*
* Description:
* The room class defines a single element in a Maze.
* A room has some number of walls, each wall is shared with another room.
* Walls are removed to create a maze.
*
* Rooms are rotated and translated with respect to their centers.
*
* Important Values:
* corners - a set of Vec2 which are scaled, rotated, and translated to represent the bounds of the room.
* RoomType - the set of supported rooms. Currently, Triangle, Square, and Hexagon.
* walls - the set of wall objects built from the set of corners. When joined with adjacent rooms, wall
* references are also updated to share the same object.
* setAdjacentRoom(Room other) - if a wall is shared by this and other, the pointers of both ware updated to point to
*                               the same object.
* */

package model.room;

import util.Pair;
import util.Vec2;
import javafx.scene.shape.Rectangle;

import java.util.*;

public abstract class Room {

    protected enum RoomType{Triangle, Square, Hexagon};

    public static final double DEFAULT_ROTATION = 0;
    public static double WALL_LENGTH = 20;
    //The center point of a triangle was experimentally found.
    public static double TRIANGLE_CENTER_HEIGHT = WALL_LENGTH * .2886751345948129;

    //Basic Room Def
    private int numWalls;
    private RoomType roomType;
    protected Vec2 location;
    private double rotation;

    //Note: Corners are relative to location
    private List<Vec2> corners;
    protected List<Wall> walls;

    public Room(RoomType roomType, Vec2 location){
        this(roomType,location,DEFAULT_ROTATION);
    }
    public Room(RoomType roomType, Vec2 location, double rotation){

        //Set def
        this.roomType = roomType;
        this.location = location;
        this.rotation = rotation;

        //Set Walls
        double wallLength = WALL_LENGTH;
        walls = new ArrayList<Wall>();
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
        double angle = -2*Math.PI/numWalls;
        corners = new ArrayList<Vec2>();
        //The strat is to go in a circle building walls starting at 0,0 then translate by the center.
        corners.add(new Vec2(0,0));
        Vec2 wallVec = new Vec2(wallLength,0);
        Vec2 avgPos = new Vec2(0,0);
        for(int wallIndex=0; wallIndex<numWalls-1;wallIndex++){
            Vec2 newCorner = corners.get(wallIndex).plus(wallVec.rotate(angle * wallIndex));
            corners.add(newCorner);
            avgPos = avgPos.plus(newCorner);
        }
        avgPos = avgPos.scale(1.0/numWalls);
        //now we need to center the corners around (0,0) and rotate them.
        for(int cornerIndex=0;cornerIndex<corners.size();cornerIndex++){
            corners.set(cornerIndex,corners.get(cornerIndex).minus(avgPos).rotate(rotation).plus(location));
        }

        //make new walls from the set of corners. ATM this assumes each wall is outside
        for(int cornerIndex=1;cornerIndex<=corners.size();cornerIndex++){
            walls.add(new Wall(corners.get(cornerIndex-1),corners.get(cornerIndex%corners.size()),this,null));
        }
    }

    public List<Vec2>getCorners(){
       return corners;
   }
    public int getNumWalls(){return numWalls;}

    //searches both rooms, looking for the overlap. Returns the resulting room if exists
    public Optional<Wall> setAdjacentRoom(Room other){
        for(int thisWallIndex = 0 ; thisWallIndex < getNumWalls(); thisWallIndex++)
            for(int otherWallIndex = 0; otherWallIndex < other.getNumWalls(); otherWallIndex++){
                if(this.walls.get(thisWallIndex).equals(other.walls.get(otherWallIndex))){
                    boolean isOpen = this.walls.get(thisWallIndex).isOpen || other.walls.get(otherWallIndex).isOpen;
                    Wall newWall = new Wall(this.walls.get(thisWallIndex).getLocations(),new Pair<Room>(this,other));
                    newWall.isOpen = isOpen;
                    walls.set(thisWallIndex,newWall);
                    other.walls.set(otherWallIndex,newWall);
                    return Optional.of(newWall);
                }
            }
        return Optional.empty();
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Room){
            Room otherRoom = (Room) object;
            return location.equals(otherRoom.location)
                    && this.rotation == otherRoom.rotation
                    && this.roomType == otherRoom.roomType;
        }
        return false;
    }

    public boolean isExternal(){
        for(Wall wall : walls)
            if(!wall.getRooms().bothPresent())
                return true;
        return false;
    }

    public List<Room> getAdjacentRooms(){
        List<Room> res = new ArrayList<>();
        for(Wall wall : walls) {
            Pair<Room> roomPair = wall.getRooms();
            if (roomPair.bothPresent())
                res.add(roomPair.getOther(this).get());
        }
        return res;
    }

    public Optional<Wall> getWall(Room adjRoom){
        for(Wall wall : walls){
            if(wall.getRooms().bothPresent() && wall.getRooms().getOther(this).get() == adjRoom)
                return Optional.of(wall);
        }
        return Optional.empty();
    }
    public Optional<Wall> getExternalWall(){
        for(Wall wall : walls){
            if(!wall.getRooms().bothPresent())
                return Optional.of(wall);
        }
        return Optional.empty();
    }

    public Rectangle getAABB(){
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for(Vec2 c : corners){
            minX = Double.min(minX,c.X());
            maxX = Double.max(maxX,c.X());
            minY = Double.min(minY,c.Y());
            maxY = Double.max(maxY,c.Y());
        }
        return new Rectangle(minX,minY,maxX-minX,maxY-minY);
    }

    public void setLocation(Vec2 newLocation){
        for(int i=0;i<corners.size();i++){
            corners.set(i,corners.get(i).minus(location).plus(newLocation));
        }

        walls.clear();
        //make new walls from the set of corners. ATM this assumes each wall is outside
        for(int i=1;i<=corners.size();i++){
            walls.add(new Wall(corners.get(i-1),corners.get(i%corners.size()),this,null));
        }
        location = newLocation;
    }

    public List<Wall> getWalls(){return walls;}
    public Vec2 getLocation(){return location;}
}
