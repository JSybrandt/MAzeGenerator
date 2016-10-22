package model.generator;

import model.Room;

class RoomNode {
    RoomNode parentNode;
    Room thisRoom;
    RoomNode(Room r){
        thisRoom = r;
        parentNode = null;
    }
    public Room findSet(){
        RoomNode currParent = this;
        while(currParent.parentNode != null) currParent = currParent.parentNode;
        return currParent.thisRoom;
    }
}
