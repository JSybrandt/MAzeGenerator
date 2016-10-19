package model;

import Util.Vec2;

import java.util.LinkedList;
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
        LinkedList<Room> roomQ = new LinkedList<>();
        roomQ.add(initRoom);
        while(!roomQ.isEmpty()){
            Room c = roomQ.poll();
            for(Wall w : c.getWalls()){
                //if is external wall
                if(!w.getRooms().bothPresent()){
                    Room newRoom;
                    List<Vec2> pts = w.getLocations().toList();
                    //get the rotation needed to match the desired wall
                    double rotation = pts.get(1).minus(pts.get(0)).angle(new Vec2(1,0));

                    //if this is a square, make triangles
                    if(c.getNumWalls() == 4){
                        Vec2 disp = new Vec2(0,Room.DEFAULT_WALL_LENGTH/3 + Room.DEFAULT_WALL_LENGTH/2);
                        disp = disp.rotate(rotation);
                        Vec2 loc = c.location.plus(disp);
                        if(loc.X() < width && loc.Y() < height && loc.X() > 0 && loc.Y() > 0) {
                            newRoom = new TriangleRoom(loc, rotation);
                            roomQ.add(newRoom);
                            roomList.add(newRoom);
                            c.setAdjacentRoom(newRoom);
                        }
                    }
                }
            }
        }
    }
}
