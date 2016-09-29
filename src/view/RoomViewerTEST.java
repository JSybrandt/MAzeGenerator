package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Room;
import model.Vec2;

import java.util.List;

/**
 * Created by jsybran on 9/29/16.
 */
public class RoomViewerTEST extends Canvas{



    public RoomViewerTEST(){
        super();
        Room room1, room2;

        setHeight(300);
        setWidth(300);

        GraphicsContext context;
        room1 = new Room(Room.RoomType.Hexagon,new Vec2(100,100), 0*Math.PI, 20);
        room2 = new Room(Room.RoomType.Square,new Vec2(100,150), 0.25*Math.PI, 50);

        context = this.getGraphicsContext2D();

        drawRoom(room1,context);
        drawRoom(room2,context);



    }

    private void drawRoom(Room r, GraphicsContext context){
        List<Vec2> corners = r.getCorners();
        int size = corners.size();
        for(int i = 1; i < corners.size()+1; i++)
            context.strokeLine(corners.get((i-1)%size).X(),corners.get((i-1)%size).Y(),corners.get(i%size).X(),corners.get(i%size).Y());
    }



}
