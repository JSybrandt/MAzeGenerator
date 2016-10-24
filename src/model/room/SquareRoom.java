/*
* Justin Sybrandt
*
* Description:
* Wrapper for Square Rooms
*
* Important Values:
*
* */

package model.room;

import util.Vec2;

public class SquareRoom extends Room {

    public SquareRoom(Vec2 location) {super(RoomType.Square,location,DEFAULT_ROTATION);}
    public SquareRoom(Vec2 location, double rotation){super(RoomType.Square,location,rotation);}

}
