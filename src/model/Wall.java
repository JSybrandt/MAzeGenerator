package model;

import Util.Pair;
import Util.Vec2;

/**
 * Created by jsybran on 9/29/16.
 */
public class Wall {

    private Pair<Vec2> locations;
    private Pair<Room> rooms;
    public boolean isOpen;

    public Wall(Vec2 loc1, Vec2 loc2, Room room1, Room room2){
        this(new Pair<Vec2>(loc1,loc2), new Pair<Room>(room1,room2));
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
