/*
* Justin Sybrandt
*
* Description:
* Wrapper for Hexagon Rooms
*
* Important Values:
*
* */

package model.room;

import util.Vec2;

public class HexRoom extends Room {
    public HexRoom(Vec2 location) {super(RoomType.Hexagon,location,DEFAULT_ROTATION);}
    public HexRoom(Vec2 location, double rotation){super(RoomType.Hexagon,location,rotation);}
}
