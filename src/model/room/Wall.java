/*
* Justin Sybrandt
*
* Description:
* This defines a wall, each room has multiple of these.
* Walls are either open or not, representing if they should be included on the maze.
* Walls also have endpoints in maze space.
*
* Important Values:
* locations - endpoints
* rooms - the pair of adjacent rooms. If either end is undefined, then the wall is external.
* isOpen - represents if the wall should be included in the final maze.
* */

package model.room;

import Util.Pair;
import Util.Vec2;

public class Wall {

    private Pair<Vec2> locations;
    private Pair<Room> rooms;
    public boolean isOpen;

    public Wall(Vec2 loc1, Vec2 loc2, Room room1, Room room2){
        this(new Pair<>(loc1,loc2), new Pair<>(room1,room2));
    }

    public Wall(Pair<Vec2>locs, Pair<Room> rooms){
        locations = locs;
        this.rooms = rooms;
        isOpen = false;
    }

    public Pair<Room> getRooms(){return rooms;}

    public Pair<Vec2> getLocations(){return locations;}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Wall)
            return locations.equals(((Wall)obj).locations);
        else
            return false;
    }
}
