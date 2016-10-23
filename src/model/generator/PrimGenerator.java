/*
* Justin Sybrandt
*
* Description:
* This class runs Prim's algorithm on a maze, removing walls based on the resulting spanning tree.
*
* Important Values:
* roomsInTree - a set of rooms used to keep track of which nodes are in the spanning tree.
* frontier - a set of rooms adjacent to the roomsInTree. Randomized to get a new room each iteration.
* */

package model.generator;

import Util.Pair;
import model.maze.Maze;
import model.room.Room;
import model.room.Wall;

import java.util.*;

public class PrimGenerator extends MazeGenerator {

    public PrimGenerator(Maze maze) {
        super(maze);
    }

    @Override
    public Pair<Room> generate() {

        Pair<Room> startEnd = getRandStartEnd();
        Room start = startEnd.getLeft().get(); //for easy access

        Set<Room> roomsInTree = new HashSet<>();
        Set<Room> frontier = new HashSet<>();

        roomsInTree.add(start);
        for(Room room : start.getAdjacentRooms()){
            frontier.add(room);
        }

        while(frontier.size() > 0){
            Room[] canidateNextRooms = frontier.toArray(new Room[frontier.size()]);
            Room nextRoom = canidateNextRooms[rand.nextInt(canidateNextRooms.length)];
            frontier.remove(nextRoom);
            roomsInTree.add(nextRoom);
            ArrayList<Room> canidateConnections = new ArrayList<>();
            for (Room neighbor : nextRoom.getAdjacentRooms()){
                if(roomsInTree.contains(neighbor))
                    canidateConnections.add(neighbor);
                else
                    frontier.add(neighbor);
            }
            Room connection = canidateConnections.get(rand.nextInt(canidateConnections.size()));

            Optional<Wall> wall = nextRoom.getWall(connection);
            if(wall.isPresent()){
                wall.get().isOpen = true;
            }
        }
        startEnd.toList().forEach(room -> room.getExternalWall().get().isOpen = true);
        return startEnd;
    }
}
