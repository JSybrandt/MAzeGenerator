package model.generator;

import Util.Pair;
import model.Maze;
import model.Room;
import model.Wall;

import java.util.*;

/**
 * Created by jsybran on 10/19/16.
 */
public class KruskalGenerator extends MazeGenerator {

    Collection<Wall> walls;
    Map<Room,RoomNode> roomNodes;

    public KruskalGenerator(Maze maze) {
        super(maze);
        walls = maze.getWalls();
        roomNodes = new HashMap<>();
        for(Room r : maze.getRooms()){
            roomNodes.put(r,new RoomNode(r));
        }
    }

    @Override
    protected Room getRandExternalRoom() {
        return super.getRandExternalRoom();
    }

    @Override
    public Pair<Room> generate() {
        Pair<Room> startEnd = getRandStartEnd();
        startEnd.toList().forEach(r -> r.getExternalWall().get().isOpen = true);

        for(Wall w : walls){
            if(w.getRooms().bothPresent()){
                Room r1 = w.getRooms().getLeft().get();
                Room r2 = w.getRooms().getRight().get();
                Room set1 = roomNodes.get(r1).findSet();
                Room set2 = roomNodes.get(r2).findSet();
                if(!set1.equals(set2)){
                    roomNodes.get(set2).parentNode = roomNodes.get(set1);
                    w.isOpen = true;
                }
            }
        }
        return startEnd;
    }
}
