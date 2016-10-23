/*
* Justin Sybrandt
*
* Description:
* Defines a maze consisting of only square rooms of size height X width.
*
* Important Values:
*
* */

package model.maze;

import Util.Vec2;
import model.room.Room;
import model.room.SquareRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareMaze extends Maze {

    private int height, width;

    SquareRoom[][] rooms;

    public SquareMaze(int height, int width){
        this.height = height;
        this.width = width;

        rooms = new SquareRoom[height][width];

        for(int rowIndex = 0; rowIndex < height; rowIndex++)
            for(int colIndex = 0; colIndex < width; colIndex++){
                rooms[rowIndex][colIndex] = new SquareRoom(getCenterOfRoom(rowIndex,colIndex),0);
            }

        for(int rowIndex = 0; rowIndex < height; rowIndex++)
            for(int colIndex = 0; colIndex < width; colIndex++){
                if(rowIndex-1 >= 0)
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex-1][colIndex]);
                if(rowIndex+1 < height)
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex+1][colIndex]);
                if(colIndex-1 >= 0)
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex][colIndex-1]);
                if(colIndex+1 < width)
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex][colIndex+1]);
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
                col * Room.WALL_LENGTH + Room.WALL_LENGTH /2,
                row * Room.WALL_LENGTH + Room.WALL_LENGTH /2);
    }

    @Override
    public List<Room> getRow(int index) {
        return Arrays.asList(rooms[index]);
    }

    @Override
    public int getNumRows() {
        return height;
    }
}
