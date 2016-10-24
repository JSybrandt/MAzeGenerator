/*
* Justin Sybrandt
*
* Description:
* This class takes a maze object and applies Eller's algorithm to open walls until the maze is perfect.
*
* Note: Eller's algorithm was generalized to support non-square mazes.
* These generalizations mainly deal with the fact that rooms may have a non-standard number of downward connections.
*
* Important Values:
* roomSets - a mapping from rooms to roomSets. Each roomSet represents the set the current room is a part of.
* makeSetConnection(Room source, Room target) - removes the wall between source and target as well as joins their sets.
*
* */

package model.generator;

import util.Pair;
import model.maze.Maze;
import model.room.Room;
import model.room.Wall;

import java.util.*;
import java.util.stream.Collectors;

public class EllerGenerator extends MazeGenerator{

    Map<Room,RoomSet> roomSets;

    private final double ADJ_JOIN_PROB = 0.5;

    Random rand;

    public EllerGenerator(Maze maze) {
        super(maze);
        rand = new Random();
        roomSets = new HashMap<>();
    }

    @Override
    public Pair<Room> generate() {

        //set each room to its own group
        maze.getRooms().forEach(room -> roomSets.put(room,new RoomSet(room)));

        //for each row
        for(int rowIndex = 0 ; rowIndex< maze.getNumRows() - 1 ; rowIndex++){
            List<Room> thisRow = maze.getRow(rowIndex);
            List<Room> nextRow = maze.getRow(rowIndex+1);

            joinAdjRooms(thisRow,ADJ_JOIN_PROB,nextRow);

            //make downward links
            //first make list for each group
            Map<Room, ArrayList<Room>> setGroups = new HashMap<>();
            for(Room room : thisRow){
                Room set = roomSets.get(room).findSet();
                if(!setGroups.containsKey(set)){
                    setGroups.put(set,new ArrayList<>());
                }
                setGroups.get(set).add(room);
            }
            for(ArrayList<Room> group : setGroups.values()){

                List<Pair<Room>> canidateVertConnections = new ArrayList<>();

                for (Room sourceRoom : group) {
                    for (Room targetRoom : getVertConnection(sourceRoom, nextRow))
                        if (roomSets.get(targetRoom).findSet() == targetRoom)//if the room is in its own set
                            canidateVertConnections.add(new Pair<>(sourceRoom, targetRoom));
                }

                //if we eliminated some options, we can go through and add in horizonal connections
                if(canidateVertConnections.size() == 0){
                    joinAdjRooms(thisRow,0,nextRow);
                }
                else {

                    int numToJoin = Integer.max(rand.nextInt(group.size()), 1);
                    for (int i = 0; i < numToJoin && canidateVertConnections.size() > 0; i++) {
                        Pair<Room> select = canidateVertConnections.get(rand.nextInt(canidateVertConnections.size()));

                        Room leftRoom = select.getLeft().get();
                        Room rightRoom = select.getRight().get();

                        makeSetConnection(leftRoom, rightRoom);

                        canidateVertConnections = canidateVertConnections.stream()
                                .filter(roomPair -> roomSets.get(roomPair.getRight().get()).findSet() == roomPair.getRight().get())
                                .collect(Collectors.toList());
                    }
                }
            }


        }

        //must join all disjoint groups in final row
        joinAdjRooms(maze.getRow(maze.getNumRows()-1),1,new ArrayList<>());

        //because the tile maze doesn't have specific rows, we are going to solve this with a special case.
        Room mainSet = roomSets.get(maze.getRooms().get(0)).findSet();
        for(Room room : maze.getRooms()){
            if(roomSets.get(room).findSet() != mainSet){
                List<Room> adjacentRooms = room.getAdjacentRooms();
                Collections.shuffle(adjacentRooms);
                for(Room neighbor : adjacentRooms){
                    if(makeSetConnection(room,neighbor))break;
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

            Room targetSet = roomSets.get(target).findSet();
            Room sourceSet = roomSets.get(source).findSet();
            if(targetSet != sourceSet) {
                optWall.get().isOpen = true;
                //join sets
                roomSets.get(targetSet).parentNode = roomSets.get(sourceSet);
                return true;
            }
        }
        return false;
    }

    private void joinAdjRooms(List<Room> row, double prob, List<Room> nextRow){
        //join adj rooms
        for(int roomIndex = 0 ; roomIndex < row.size()-1; roomIndex++){
            Room leftRoom = row.get(roomIndex);
            Room rightRoom = row.get(roomIndex+1);
            List<Room> leftTargets = getVertConnection(leftRoom,nextRow);
            List<Room> rightTargets = getVertConnection(rightRoom,nextRow);
            if(rand.nextDouble() < prob || leftTargets.size()==0 || rightTargets.size()==0){
                Optional<Wall> optWall = leftRoom.getWall(rightRoom);
                if(optWall.isPresent()) {
                    Room leftSet = roomSets.get(leftRoom).findSet();
                    Room rightSet = roomSets.get(rightRoom).findSet();
                    if (!leftSet.equals(rightSet)) {
                        roomSets.get(rightSet).parentNode = roomSets.get(leftSet);
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
