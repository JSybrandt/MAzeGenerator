package model;

import Util.Vec2;

/**
 * Created by jsybran on 10/19/16.
 */
public class TriangleRoom extends Room {
    public TriangleRoom(Vec2 location) {super(RoomType.Triangle,location,DEFAULT_ROTATION);}
    public TriangleRoom(Vec2 location, double rotation){super(RoomType.Triangle,location,rotation);}
}
