/*
* Justin Sybrandt
*
* Description:
* Represents a maze of all hexagon shaped rooms. Creates these rooms and joins their walls given a width and height.
*
* Important Values:
* HEX_WIDTH - used to calculate the center location of each hexagon.
* HEX_HEIGHT - used to calculate the center location of each hexagon.
*
* */

package model.maze;

import Util.Vec2;
import model.room.Room;
import model.room.HexRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class HexMaze extends Maze {
    private int height, width;

    HexRoom[][] rooms;

    private final double RATIO_SIDELENGTH2WIDTH = sqrt(3);
    private final double RATIO_SIDELENGTH2HIEGHT = 1.5;
    private final double HEX_WIDTH = Room.WALL_LENGTH * RATIO_SIDELENGTH2WIDTH;
    private final double HEX_HEIGHT = Room.WALL_LENGTH * RATIO_SIDELENGTH2HIEGHT;

    public HexMaze(int height, int width) {
        this.height = height;
        this.width = width;

        rooms = new HexRoom[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                rooms[i][j] = new HexRoom(getCenterOfRoom(i, j),PI/2);
            }

        for (int rowIndex = 0; rowIndex < height; rowIndex++)
            for (int colIndex = 0; colIndex < width; colIndex++) {

                if(rowIndex-1 >= 0) {
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex - 1][colIndex]);
                    if(colIndex+1 < width)
                        rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex - 1][colIndex + 1]);
                }
                if (rowIndex + 1 < height) {
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex + 1][colIndex]);
                    if(colIndex+1 < width)
                        rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex + 1][colIndex + 1]);
                }
                if (colIndex - 1 >= 0)
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex][colIndex - 1]);
                if (colIndex + 1 < width)
                    rooms[rowIndex][colIndex].setAdjacentRoom(rooms[rowIndex][colIndex + 1]);
            }

        roomList = new ArrayList<Room>();
        for (Room[] row : rooms) {
            for (Room room : row) {
                roomList.add(room);
            }
        }

    }

    //adjusts locations based on row and col
    private Vec2 getCenterOfRoom(int row, int col){
        return new Vec2(
                col * HEX_WIDTH + ((row%2==0) ? HEX_WIDTH /2 : HEX_WIDTH),
                row * HEX_HEIGHT + HEX_HEIGHT);
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
