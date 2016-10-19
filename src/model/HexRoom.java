package model;

import Util.Vec2;

/**
 * Created by jsybran on 10/19/16.
 */
public class HexRoom extends Room {
    public HexRoom(Vec2 location) {super(RoomType.Hexagon,location,DEFAULT_ROTATION);}
    public HexRoom(Vec2 location, double rotation){super(RoomType.Hexagon,location,rotation);}
}
