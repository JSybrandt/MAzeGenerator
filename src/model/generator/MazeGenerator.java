/*
* Justin Sybrandt
*
* Description:
* This class defines a common interface for all maze generators.
* Regardless of their algorithm, a maze generator should require only a maze object,
* and should remove walls when generate is called.
*
* The maze generator will also return the start and end rooms for the resulting maze.
*
* Important Values:
* extRooms - a list of external rooms, used to make finding the edge of the maze faster.
*
* */

package model.generator;

import util.Pair;
import model.maze.Maze;
import model.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MazeGenerator {

    protected Maze maze;
    private List<Room> extRooms;
    protected Random rand;

    public MazeGenerator(Maze maze){
        this.maze = maze;
        extRooms = new ArrayList<>();
        for(Room room : maze.getRooms()){
            if(room.isExternal())extRooms.add(room);
        }
        rand = new Random();
    }

    protected Room getRandExternalRoom(){
        return extRooms.get(rand.nextInt(extRooms.size()));
    }

    //RETURNS START, END
    public abstract Pair<Room> generate();

    protected Pair<Room> getRandStartEnd(){
        Room start = getRandExternalRoom();
        Room end = getRandExternalRoom();
        while(start.equals(end) && extRooms.size() > 1) end = getRandExternalRoom();
        return new Pair<>(start,end);
    }

}
