package model.generator;

import Util.Pair;
import model.Maze;
import model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class can be given a maze object and will remove walls to create a tree like structure.
 */
public abstract class MazeGenerator {

    private Maze maze;
    private List<Room> extRooms;
    protected Random rand;

    public MazeGenerator(Maze maze){
        this.maze = maze;
        extRooms = new ArrayList<>();
        for(Room r : maze.getRooms()){
            if(r.isExternal())extRooms.add(r);
        }
        rand = new Random();
    }

    protected Room getRandExternalRoom(){
        return extRooms.get(rand.nextInt(extRooms.size()));
    }

    //RETURNS START, END
    public abstract Pair<Room> generate();

}
