/*
* Justin Sybrandt
*
* Description:
* This class makes grouping rooms together as sets a fast operation.
* Follows the standard union-find paradigm.
*
* Important Values:
* parentNode - is null, thisRoom is the set leader.
*
* */

package model.generator;

import model.room.Room;

class RoomSet {
    RoomSet parentNode;
    Room thisRoom;
    RoomSet(Room r){
        thisRoom = r;
        parentNode = null;
    }
    public Room findSet(){
        RoomSet currParent = this;
        while(currParent.parentNode != null) currParent = currParent.parentNode;
        return currParent.thisRoom;
    }
}
