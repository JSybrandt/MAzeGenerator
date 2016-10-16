package model.generator;

import Util.Pair;
import model.Maze;
import model.Room;
import model.Wall;

import java.util.*;

/**
 * Created by nalta on 10/16/2016.
 */
public class PrimGenerator extends MazeGenerator {

    public PrimGenerator(Maze maze) {
        super(maze);
    }

    @Override
    public Pair<Room> generate() {
        Room start = getRandExternalRoom();
        Room end = getRandExternalRoom();
        while(start.equals(end)) end = getRandExternalRoom();

        Set<Room> roomsInTree = new HashSet<>();
        Set<Room> frontier = new HashSet<>();

        roomsInTree.add(start);
        for(Room r : start.getAdjacentRooms()){
            frontier.add(r);
        }

        while(frontier.size() > 0){
            Room[] canidateNextRooms = frontier.toArray(new Room[frontier.size()]);
            Room nextRoom = canidateNextRooms[rand.nextInt(canidateNextRooms.length)];
            frontier.remove(nextRoom);
            roomsInTree.add(nextRoom);
            ArrayList<Room> canidateConnections = new ArrayList<>();
            for (Room neighbor : nextRoom.getAdjacentRooms()){
                if(roomsInTree.contains(neighbor)){
                    canidateConnections.add(neighbor);
                }
                else{
                    frontier.add(neighbor);
                }
            }
            Room connection = canidateConnections.get(rand.nextInt(canidateConnections.size()));

            Optional<Wall> wall = nextRoom.getWall(connection);
            if(wall.isPresent()){
                wall.get().isOpen = true;
            }
        }
        start.getExternalWall().get().isOpen = true;
        end.getExternalWall().get().isOpen = true;
        return new Pair<Room>(start,end);
    }
}
