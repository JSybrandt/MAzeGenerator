/*
* Justin Sybrandt
*
* Description:
* This class runs Kruskal's algorithm on a maze, removing walls based on the resulting spanning tree.
*
* Important Values:
* roomSets - a mapping from rooms to roomSets. Each roomSet represents the set the current room is a part of.
* */

package model.generator;

import util.Pair;
import model.maze.Maze;
import model.room.Room;
import model.room.Wall;

import java.util.*;

public class KruskalGenerator extends MazeGenerator {

    Collection<Wall> walls;
    Map<Room,RoomSet> roomSets;

    public KruskalGenerator(Maze maze) {
        super(maze);
        walls = maze.getWalls();
        roomSets = new HashMap<>();
        for(Room room : maze.getRooms()){
            roomSets.put(room,new RoomSet(room));
        }
    }

    @Override
    protected Room getRandExternalRoom() {
        return super.getRandExternalRoom();
    }

    @Override
    public Pair<Room> generate() {
        Pair<Room> startEnd = getRandStartEnd();
        startEnd.toList().forEach(room -> room.getExternalWall().get().isOpen = true);

        for(Wall wall : walls){
            if(wall.getRooms().bothPresent()){
                Room leftRoom = wall.getRooms().getLeft().get();
                Room rightroom = wall.getRooms().getRight().get();
                Room leftSet = roomSets.get(leftRoom).findSet();
                Room rightSet = roomSets.get(rightroom).findSet();
                if(!leftSet.equals(rightSet)){
                    roomSets.get(rightSet).parentNode = roomSets.get(leftSet);
                    wall.isOpen = true;
                }
            }
        }
        return startEnd;
    }
}
