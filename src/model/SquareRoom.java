package model;

import Util.Vec2;

/**
 * Created by jsybran on 10/10/16.
 */
public class SquareRoom extends Room {

    public SquareRoom(Vec2 location) {super(RoomType.Square,location,DEFAULT_ROTATION);}
    public SquareRoom(Vec2 location, double rotation){super(RoomType.Square,location,rotation);}

}
