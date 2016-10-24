/*
* Justin Sybrandt
*
* Description:
* Constructs a maze consisting of square and triangle rooms in a tessellation pattern.
* Note: the height and width parameters define only the pattern of square rooms.
*
* Important Values:
* rows - a heuristic based algorithm is used to segment off rows of the tessellation for Eller's algorithm.
*        This approach leads to cleaner code than an index based approach.
* */

package model.maze;

import util.Vec2;
import javafx.scene.shape.Rectangle;
import model.room.Room;
import model.room.SquareRoom;
import model.room.TriangleRoom;

import java.util.*;

import static java.lang.Math.PI;

public class TileMaze extends Maze {

    SquareRoom[][] squareRooms;
    List<List<Room>> rows;

    public TileMaze(int squareRows, int squareCols) {
        super();

        rows = new ArrayList<>();
        squareRooms = new SquareRoom[squareRows][squareCols];
        double lastX=0;
        double lastY=0;
        Rectangle aabb = new Rectangle(0,0,0,0);
        for(int squareRowIndex = 0; squareRowIndex < squareRows; squareRowIndex++) {
            for (int squareColIndex = 0; squareColIndex < squareCols; squareColIndex++) {
                double rot = ((squareRowIndex + squareColIndex) % 2 == 0) ? PI / 3 : -PI / 3;
                SquareRoom squareRoom = new SquareRoom(new Vec2(0, 0), rot);
                aabb = squareRoom.getAABB();
                squareRoom.setLocation(new Vec2(lastX+aabb.getWidth()/2,lastY+aabb.getHeight()/2));
                roomList.add(squareRoom);
                squareRooms[squareRowIndex][squareColIndex] = squareRoom;
                lastX += aabb.getWidth();
            }
            lastX = 0;
            lastY += aabb.getHeight();
        }

        for(int triangleRowIndex=0; triangleRowIndex < squareRows-1; triangleRowIndex++){
            for(int triangleColIndex=0; triangleColIndex < squareCols-1; triangleColIndex++){
                List<SquareRoom> adjRooms = new ArrayList<>();
                adjRooms.add(squareRooms[triangleRowIndex][triangleColIndex]);
                adjRooms.add(squareRooms[triangleRowIndex][triangleColIndex+1]);
                adjRooms.add(squareRooms[triangleRowIndex+1][triangleColIndex]);
                adjRooms.add(squareRooms[triangleRowIndex+1][triangleColIndex+1]);

                Vec2 center = new Vec2(0,0);
                for(Room room : adjRooms)
                    center = center.plus(room.getLocation());
                center = center.scale(1.0/adjRooms.size());
                Vec2 disp = new Vec2(0,Room.TRIANGLE_CENTER_HEIGHT);
                double rotation = 0;
                if((triangleRowIndex+triangleColIndex)%2==0){
                    disp = disp.rotate(PI/2);
                    rotation = PI/2;
                }
                //the tesselation pattern has pairs of triangles
                TriangleRoom triRoom1 = new TriangleRoom(center.minus(disp),rotation);
                TriangleRoom triRoom2 = new TriangleRoom(center.plus(disp),rotation + PI);
                roomList.add(triRoom1);
                roomList.add(triRoom2);
                for(Room room: adjRooms){
                    triRoom1.setAdjacentRoom(room);
                    triRoom2.setAdjacentRoom(room);
                }
                triRoom1.setAdjacentRoom(triRoom2);

            }
        }

        Set<Room> seenRooms = new HashSet<>();
        for(int squareRowIndex = 0 ; squareRowIndex < squareRows; squareRowIndex++){
            Set<Room> preferedRooms = new HashSet<>();
            preferedRooms.addAll(Arrays.asList(squareRooms[squareRowIndex]));
            ArrayList<Room> row = new ArrayList<>();
            Room currentRoom = squareRooms[squareRowIndex][0];
            while(currentRoom != null){
                row.add(currentRoom);
                seenRooms.add(currentRoom);
                List<Room> adj = currentRoom.getAdjacentRooms();
                Vec2 loc = currentRoom.getLocation();
                currentRoom = null;
                for(Room room : adj){
                    if(!seenRooms.contains(room) && (preferedRooms.contains(room) ||  room.getLocation().X() > loc.X())){
                        currentRoom = room;
                        if(preferedRooms.contains(room)) break;
                    }
                }

            }
            rows.add(row);
        }

    }

    @Override
    public List<Room> getRow(int index) {
        return rows.get(index);
    }

    @Override
    public int getNumRows() {
        return rows.size();
    }
}
