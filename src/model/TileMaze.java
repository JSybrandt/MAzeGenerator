package model;

import Util.Vec2;
import javafx.scene.shape.Rectangle;

import java.util.*;

import static java.lang.Math.PI;

/**
 * Created by jsybran on 10/19/16.
 */
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
        for(int sr = 0; sr < squareRows; sr++) {
            for (int sc = 0; sc < squareCols; sc++) {
                double rot = ((sr + sc) % 2 == 0) ? PI / 3 : -PI / 3;
                SquareRoom squareRoom = new SquareRoom(new Vec2(0, 0), rot);
                aabb = squareRoom.getAABB();
                squareRoom.setLocation(new Vec2(lastX+aabb.getWidth()/2,lastY+aabb.getHeight()/2));
                roomList.add(squareRoom);
                squareRooms[sr][sc] = squareRoom;
                lastX += aabb.getWidth();
            }
            lastX = 0;
            lastY += aabb.getHeight();
        }

        for(int tr=0; tr < squareRows-1; tr++){
            for(int tc=0; tc < squareCols-1; tc++){
                List<SquareRoom> adjRooms = new ArrayList<>();
                adjRooms.add(squareRooms[tr][tc]);
                adjRooms.add(squareRooms[tr][tc+1]);
                adjRooms.add(squareRooms[tr+1][tc]);
                adjRooms.add(squareRooms[tr+1][tc+1]);

                Vec2 center = new Vec2(0,0);
                for(Room r : adjRooms)
                    center = center.plus(r.location);
                center = center.scale(1.0/adjRooms.size());
                Vec2 disp = new Vec2(0,Room.TRIANGLE_CENTER_HEIGHT);
                double rotation = 0;
                if((tr+tc)%2==0){
                    disp = disp.rotate(PI/2);
                    rotation = PI/2;
                }
                TriangleRoom t1 = new TriangleRoom(center.minus(disp),rotation);
                TriangleRoom t2 = new TriangleRoom(center.plus(disp),rotation + PI);
                roomList.add(t1);
                roomList.add(t2);
                for(Room r: adjRooms){
                    t1.setAdjacentRoom(r);
                    t2.setAdjacentRoom(r);
                }
                t1.setAdjacentRoom(t2);

            }
        }

        Set<Room> seenRooms = new HashSet<>();
        for(int i = 0 ; i < squareRows; i++){
            Set<Room> preferedRooms = new HashSet<>();
            preferedRooms.addAll(Arrays.asList(squareRooms[i]));
            ArrayList<Room> row = new ArrayList<>();
            Room currentRoom = squareRooms[i][0];
            while(currentRoom != null){
                row.add(currentRoom);
                seenRooms.add(currentRoom);
                List<Room> adj = currentRoom.getAdjacentRooms();
                Vec2 loc = currentRoom.location;
                currentRoom = null;
                for(Room r : adj){
                    if(!seenRooms.contains(r) && (preferedRooms.contains(r) ||  r.location.X() > loc.X())){
                        currentRoom = r;
                        if(preferedRooms.contains(r)) break;
                    }
                }

            }
            rows.add(row);
        }

        /*
        List<Room> prevRow = Arrays.asList(squareRooms[0]);
        rows.add(prevRow);
        Set<Room> seenRooms = new HashSet<>();
        seenRooms.addAll(prevRow);
        while(seenRooms.size() < roomList.size()){
            List<Room> frontier = new ArrayList<>();
            for(Room r : prevRow){
                for(Room newRoom : r.getAdjacentRooms()){
                    if(!seenRooms.contains(newRoom)){
                        frontier.add(newRoom);
                        seenRooms.add(newRoom);
                    }
                }
            }
            rows.add(frontier);
            prevRow = frontier;
        }
        */

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
