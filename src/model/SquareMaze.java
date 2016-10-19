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
                col * Room.DEFAULT_WALL_LENGTH + Room.DEFAULT_WALL_LENGTH/2,
                row * Room.DEFAULT_WALL_LENGTH + Room.DEFAULT_WALL_LENGTH/2);
    }

}
