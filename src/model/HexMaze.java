package model;

import Util.Vec2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

/**
 * Created by jsybran on 10/19/16.
 */
public class HexMaze extends Maze {
    private int height, width;

    HexRoom[][] rooms;

    private final double R_SIZE_2_WIDTH = sqrt(3);
    private final double HEX_WIDTH = Room.DEFAULT_WALL_LENGTH * R_SIZE_2_WIDTH;
    private final double HEX_HEIGHT = Room.DEFAULT_WALL_LENGTH * 1.5;

    public HexMaze(int height, int width) {
        this.height = height;
        this.width = width;

        rooms = new HexRoom[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                rooms[i][j] = new HexRoom(getCenterOfRoom(i, j),PI/2);
            }

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {

                if(i-1 >= 0) {
                    rooms[i][j].setAdjacentRoom(rooms[i - 1][j]);
                    if(j+1 < width)
                        rooms[i][j].setAdjacentRoom(rooms[i - 1][j + 1]);
                }
                if (i + 1 < height) {
                    rooms[i][j].setAdjacentRoom(rooms[i + 1][j]);
                    if(j+1 < width)
                        rooms[i][j].setAdjacentRoom(rooms[i + 1][j + 1]);
                }
                if (j - 1 >= 0)
                    rooms[i][j].setAdjacentRoom(rooms[i][j - 1]);
                if (j + 1 < width)
                    rooms[i][j].setAdjacentRoom(rooms[i][j + 1]);
            }

        roomList = new ArrayList<Room>();
        for (Room[] row : rooms) {
            for (Room room : row) {
                roomList.add(room);
            }
        }

    }

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
