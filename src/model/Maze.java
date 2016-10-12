package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Justin Sybrandt
 *  The Maze abstract class is the parent of GridMaze, HexMaze, and TesselationMaze
 *  This class defines a common interface and implimentation.
 */
public abstract class Maze {

    List<Room> roomList;

    public abstract void generateMaze();

    public List<Room> getRooms(){return roomList;};

    public Collection<Wall> getWalls(){
        HashSet<Wall> wallSet = new HashSet<>();
        for (Room r: getRooms()){
            for(Wall w: r.walls) {
                wallSet.add(w);
            }
        }
        return wallSet;
    };

}
