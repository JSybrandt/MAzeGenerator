package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import Util.Pair;
import model.Room;
import model.SquareRoom;
import Util.Vec2;

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
        room1 = new SquareRoom(new Vec2(100,100), 0*Math.PI);
        room2 = new SquareRoom(new Vec2(100,150), 0.25*Math.PI);

        room2.setLocation(new Vec2(200,200));

        context = this.getGraphicsContext2D();

        drawRoom(room1,context);
        drawRoom(room2,context);

        Pair<Integer> test = new Pair<>(5);
        Pair<Integer> test2 = new Pair<>(null,6);

        if(test.equals(test2))
            System.out.println("WE DID IT R2");



    }

    private void drawRoom(Room r, GraphicsContext context){
        List<Vec2> corners = r.getCorners();
        int size = corners.size();
        for(int i = 1; i < corners.size()+1; i++)
            context.strokeLine(corners.get((i-1)%size).X(),corners.get((i-1)%size).Y(),corners.get(i%size).X(),corners.get(i%size).Y());
    }



}
