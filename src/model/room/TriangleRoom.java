/*
* Justin Sybrandt
*
* Description:
* Wrapper for Triangle Rooms
*
* Important Values:
*
* */

package model.room;

import Util.Vec2;

public class TriangleRoom extends Room {
    public TriangleRoom(Vec2 location) {super(RoomType.Triangle,location,DEFAULT_ROTATION);}
    public TriangleRoom(Vec2 location, double rotation){super(RoomType.Triangle,location,rotation);}
}
