package model.generator;

import Util.Pair;
import model.Maze;
import model.Room;
import model.Wall;

import java.util.*;

/**
 * Created by jsybran on 10/19/16.
 */
public class EllerGenerator extends MazeGenerator{

    Map<Room,RoomNode> roomNodes;

    private final double ADJ_JOIN_PROB = 0.5;
    private final double VERT_JOIN_PROB = 0.2;

    Random rand;

    public EllerGenerator(Maze maze) {
        super(maze);
        rand = new Random();
        roomNodes = new HashMap<>();
    }

    @Override
    public Pair<Room> generate() {

        //set each room to its own group
        maze.getRooms().forEach(room -> roomNodes.put(room,new RoomNode(room)));

        //for each row
        for(int rowIndex = 0 ; rowIndex< maze.getNumRows() - 1 ; rowIndex++){
            List<Room> thisRow = maze.getRow(rowIndex);
            List<Room> nextRow = maze.getRow(rowIndex+1);

            joinAdjRooms(thisRow,ADJ_JOIN_PROB);

            //make downward links
            //first make list for each group
            Map<Room, ArrayList<Room>> setGroups = new HashMap<>();
            for(Room r : thisRow){
                Room set = roomNodes.get(r).findSet();
                if(!setGroups.containsKey(set)){
                    setGroups.put(set,new ArrayList<>());
                }
                setGroups.get(set).add(r);
            }
            for(ArrayList<Room> group : setGroups.values()){
                ArrayList<Room> vertConnections = new ArrayList<>();
                Room select = group.get(rand.nextInt(group.size()));
                group.remove(select);
                vertConnections.add(select);
                for(Room r : group){
                    if(rand.nextDouble() < VERT_JOIN_PROB){
                        vertConnections.add(r);
                    }
                }
                for(Room r : vertConnections){
                    makeVertConnection(r,nextRow);
                }
            }
        }

        //must join all disjoint groups in final row
        joinAdjRooms(maze.getRow(maze.getNumRows()-1),1);


        Pair<Room> startEnd = getRandStartEnd();
        startEnd.toList().forEach(room -> room.getExternalWall().get().isOpen = true);
        return startEnd;
    }

    private void makeVertConnection(Room target, List<Room> lowerRow){
        Set<Room> adj = new HashSet<>();
        adj.addAll(target.getAdjacentRooms());
        Set<Room> row = new HashSet<>();
        row.addAll(lowerRow);
        adj.retainAll(row);
        for(Room r : adj) {
            Optional<Wall> optWall = target.getWall(r);
            if(optWall.isPresent()) {
                optWall.get().isOpen = true;
                Room set1 = roomNodes.get(target).findSet();
                Room set2 = roomNodes.get(r).findSet();
                //join sets
                roomNodes.get(set1).parentNode = roomNodes.get(set2);
            }
        }
    }

    private void joinAdjRooms(List<Room> row, double prob){
        //join adj rooms
        for(int roomIndex = 0 ; roomIndex < row.size()-1; roomIndex++){
            if(rand.nextDouble() < prob){
                Room r1 = row.get(roomIndex);
                Room r2 = row.get(roomIndex+1);
                Optional<Wall> optWall = r1.getWall(r2);
                if(optWall.isPresent()) {
                    Room set1 = roomNodes.get(r1).findSet();
                    Room set2 = roomNodes.get(r2).findSet();
                    if (!set1.equals(set2)) {
                        roomNodes.get(set2).parentNode = roomNodes.get(set1);
                        optWall.get().isOpen = true;
                    }
                }
            }
        }
    }
}
