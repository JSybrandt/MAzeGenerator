package model;

import Util.Vec2;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by jsybran on 10/19/16.
 */
public class TileMaze extends Maze {

    public TileMaze(double height, double width) {
        super();
        Room initRoom = new SquareRoom(new Vec2(Room.DEFAULT_WALL_LENGTH/2,Room.DEFAULT_WALL_LENGTH/2));
        roomList.add(initRoom);
        Queue<Room> roomQ = new PriorityQueue<>();
        roomQ.add(initRoom);
        while(!roomQ.isEmpty()){
            Room c = roomQ.poll();
            for(Wall w : c.getWalls()){
                //if is external wall
                if(!w.getRooms().bothPresent()){
                    Room newRoom;
                    List<Vec2> pts = w.getLocations().toList();
                    //get the rotation needed to match the desired wall
                    double rotation = pts.get(2).minus(pts.get(1)).angle(new Vec2(1,0));

                    if(c.getNumWalls() == 4){
                        //newRoom = new TriangleRoom()
                    }
                }
            }
        }
    }
}
