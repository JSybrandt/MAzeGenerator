package model;

import Util.Pair;
import Util.Vec2;

import java.util.*;

/**
 * The square maze class defines a maze with exclusivly square rooms
 */
public class SquareMaze extends Maze {

    private int height, width;

    SquareRoom[][] rooms;


    //TODO: Need to set the location of each room
    public SquareMaze(int height, int width){
        this.height = height;
        this.width = width;

        rooms = new SquareRoom[height][width];

        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++){
                rooms[i][j] = new SquareRoom(getCenterOfRoom(i,j),0);
            }

        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++){
                if(i-1 >= 0)
                    rooms[i][j].setAdjacentRoom(rooms[i-1][j]);
                if(i+1 < height)
                    rooms[i][j].setAdjacentRoom(rooms[i+1][j]);
                if(j-1 >= 0)
                    rooms[i][j].setAdjacentRoom(rooms[i][j-1]);
                if(j+1 < width)
                    rooms[i][j].setAdjacentRoom(rooms[i][j+1]);
            }

        roomList = new ArrayList<Room>();
        for(Room[] row : rooms){
            for(Room room : row){
                roomList.add(room);
            }
        }
    }

    private Vec2 getCenterOfRoom(int row, int col){
        return new Vec2(
                row * Room.DEFAULT_WALL_LENGTH + Room.DEFAULT_WALL_LENGTH/2,
                col * Room.DEFAULT_WALL_LENGTH + Room.DEFAULT_WALL_LENGTH/2);
    }


    public void generateMaze(){
        Random rand = new Random();
        Room currRoom = rooms[0][0];
        while(currRoom != null){
            int wallIndex = (rand.nextDouble() < 0.5 ? 1: 0);
            Wall selectedWall = currRoom.getWall(wallIndex);
            Optional<Room> nextRoom = selectedWall.getRooms().getOther(currRoom);
            if(nextRoom.isPresent()){
                System.out.println("Eliminated Wall");
                Optional<Pair<Wall>> wallPair = currRoom.getAdjacentWalls(nextRoom.get());
                if(wallPair.isPresent())
                    wallPair.get().toList().forEach(wall -> wall.isOpen = true);
                currRoom = nextRoom.get();
            }else currRoom = null;
        }
    }

}
