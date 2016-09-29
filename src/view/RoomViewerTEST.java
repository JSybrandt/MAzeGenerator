package view;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.Room;
import model.Vec2;

import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by jsybran on 9/29/16.
 */
public class RoomViewerTEST extends Canvas{



    public RoomViewerTEST(){
        super();
        Room room;

        setHeight(300);
        setWidth(300);

        GraphicsContext context;
        room = new Room(Room.RoomType.Hexagon,new Vec2(100,100), 0*Math.PI, 20);

        context = this.getGraphicsContext2D();

        context.setStroke(Color.BLACK);

        List<Vec2> corners = room.getCorners();

        int size = corners.size();
        for(int i = 1; i < corners.size()+1; i++)
            context.strokeLine(corners.get((i-1)%size).X(),corners.get((i-1)%size).Y(),corners.get(i%size).X(),corners.get(i%size).Y());
        for(Vec2 vec : corners)
            System.out.println(vec);

        setOnMouseClicked(event -> System.out.println(event));
    }



}
