package model.generator;

import Util.Pair;
import model.Maze;
import model.Room;
import model.Wall;

import java.util.*;
import java.util.stream.Collectors;

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

            joinAdjRooms(thisRow,ADJ_JOIN_PROB,nextRow);

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

                List<Pair<Room>> canidateVertConnections = new ArrayList<>();

                for (Room r1 : group) {
                    for (Room r2 : getVertConnection(r1, nextRow))
                        if (roomNodes.get(r2).findSet() == r2)//if the room is in its own set
                            canidateVertConnections.add(new Pair<>(r1, r2));
                }

                //if we eliminated some options, we can go through and add in horizonal connections
                if(canidateVertConnections.size() == 0){
                    joinAdjRooms(thisRow,0,nextRow);
                }
                else {

                    int numToJoin = Integer.max(rand.nextInt(group.size()), 1);
                    for (int i = 0; i < numToJoin && canidateVertConnections.size() > 0; i++) {
                        Pair<Room> select = canidateVertConnections.get(rand.nextInt(canidateVertConnections.size()));

                        Room r1 = select.getLeft().get();
                        Room r2 = select.getRight().get();

                        makeSetConnection(r1, r2);

                        canidateVertConnections = canidateVertConnections.stream()
                                .filter(roomPair -> roomNodes.get(roomPair.getRight().get()).findSet() == roomPair.getRight().get())
                                .collect(Collectors.toList());
                    }
                }
            }


        }

        //must join all disjoint groups in final row
        joinAdjRooms(maze.getRow(maze.getNumRows()-1),1,new ArrayList<>());

        //because the tile maze doesn't have specific rows, we are going to solve this with a special case.
        Room mainSet = roomNodes.get(maze.getRooms().get(0)).findSet();
        for(Room r : maze.getRooms()){
            if(roomNodes.get(r).findSet() != mainSet){
                List<Room> adj = r.getAdjacentRooms();
                Collections.shuffle(adj);
                for(Room neighbor : adj){
                    if(makeSetConnection(r,neighbor))break;
                }
            }
        }


        Pair<Room> startEnd = getRandStartEnd();
        startEnd.toList().forEach(room -> room.getExternalWall().get().isOpen = true);
        return startEnd;
    }

    private boolean makeSetConnection(Room source, Room target){
        Optional<Wall> optWall = source.getWall(target);
        if (optWall.isPresent()) {

            Room set1 = roomNodes.get(target).findSet();
            Room set2 = roomNodes.get(source).findSet();
            if(set1 != set2) {
                optWall.get().isOpen = true;
                //join sets
                roomNodes.get(set1).parentNode = roomNodes.get(set2);
                return true;
            }
        }
        return false;
    }

    private void joinAdjRooms(List<Room> row, double prob, List<Room> nextRow){
        //join adj rooms
        for(int roomIndex = 0 ; roomIndex < row.size()-1; roomIndex++){
            Room r1 = row.get(roomIndex);
            Room r2 = row.get(roomIndex+1);
            List<Room> v1 = getVertConnection(r1,nextRow);
            List<Room> v2 = getVertConnection(r2,nextRow);
            if(rand.nextDouble() < prob || v1.size()==0 || v2.size()==0){
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

    private List<Room> getVertConnection(Room room, List<Room> nextRow){
        Set<Room> row = new HashSet<>();
        row.addAll(nextRow);
        Set<Room> adj = new HashSet<>();
        adj.addAll(room.getAdjacentRooms());
        row.retainAll(adj);
        ArrayList<Room> ret = new ArrayList<>();
        ret.addAll(row);
        return ret;
    }
}
