/*
* Justin Sybrandt
*
* Description:
*  The Maze abstract class is the parent of SquareMaze, HexMaze, and TileMaze
*  This class defines a common interface for all mazes, used by the maze generators.
*
* Important Values:
* getNumRows - used by Eller's algorithm for iterative maze generation.
*
*
* */

package model.maze;

import model.room.Room;
import model.room.Wall;

import java.util.*;

public abstract class Maze {

    protected List<Room> roomList;

    public Maze(){roomList = new ArrayList<>();}

    public List<Room> getRooms(){return roomList;};

    public Collection<Wall> getWalls(){
        HashSet<Wall> wallSet = new HashSet<>();
        for (Room room: getRooms()){
            for(Wall wall: room.getWalls()) {
                wallSet.add(wall);
            }
        }
        return wallSet;
    };

    public abstract List<Room> getRow(int index);
    public abstract int getNumRows();

}
