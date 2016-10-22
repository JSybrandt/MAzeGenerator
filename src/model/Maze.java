package model;

import java.util.*;

/**
 *  Justin Sybrandt
 *  The Maze abstract class is the parent of GridMaze, HexMaze, and TesselationMaze
 *  This class defines a common interface and implimentation.
 */
public abstract class Maze {

    protected List<Room> roomList;

    public Maze(){roomList = new ArrayList<>();}

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

    public abstract List<Room> getRow(int index);
    public abstract int getNumRows();

}
