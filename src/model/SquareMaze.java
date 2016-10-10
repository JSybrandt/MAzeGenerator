package model;

import java.util.*;

/**
 * The square maze class defines a maze with exclusivly square rooms
 */
public class SquareMaze {

    private int height, width;

    SquareRoom[][] rooms;


    //TODO: Need to set the location of each room
    public SquareMaze(int height, int width){
        this.height = height;
        this.width = width;

        rooms = new SquareRoom[height][width];

        for(int i = 1; i < height -1; i++)
            for(int j = 1; j < width -1; j++){
                rooms[i][j].setAdjacentRoom(rooms[i-1][j]);
                rooms[i][j].setAdjacentRoom(rooms[i+1][j]);
                rooms[i][j].setAdjacentRoom(rooms[i][j-1]);
                rooms[i][j].setAdjacentRoom(rooms[i][j+1]);
            }
    }


    public void generateMaze(){
        Stack<Room> stack = new Stack<>();
        Set<Room> seenRooms = new HashSet<>();
        stack.push(rooms[0][0]);

        while(!stack.empty()){
            Room curr = stack.pop();
            boolean madeNewRoom = false;
            if(!seenRooms.contains(curr)){
                seenRooms.add(curr);
                List<Wall> wallList = curr.getInternalWalls();
                Collections.shuffle(wallList);
                for(Wall w : wallList){
                    Optional<Room> otherRoom = w.getRooms().getOther(curr);
                    if(otherRoom.isPresent() && !seenRooms.contains(otherRoom.get())) {
                        stack.push(otherRoom.get());
                        if(!madeNewRoom) {
                            w.isOpen = true;
                            madeNewRoom = true;
                        }
                    }
                }
            }
        }
    }

}
