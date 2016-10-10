package model;

/**
 * Created by jsybran on 10/10/16.
 */
public class SquareRoom extends Room {
    public SquareRoom(Vec2 location, double rotation){super(RoomType.Square,location,rotation);}

    public enum WallNames{North, South, East, West};



}
