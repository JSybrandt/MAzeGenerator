package controller;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * This class holds the stubs for all controller callbacks.
 */
public abstract class Controller {

    public Controller(Node node){
        registerCallbacks(node);
    }

    public void registerCallbacks(Node node){
        node.setOnMouseClicked(this::onViewMouseClicked);
        node.setOnKeyPressed(this::onViewKeyPressed);
        node.setOnKeyReleased(this::onViewKeyReleased);
        node.setOnMouseMoved(this::onViewMouseMoved);
        node.setOnScroll(this::onViewScroll);
    }

    public void onViewMouseClicked(MouseEvent event){}

    public void onViewKeyPressed(KeyEvent event){}

    public void onViewKeyReleased(KeyEvent event){}

    public void onViewMouseMoved(MouseEvent event){}

    public void onViewScroll(ScrollEvent event){}

    public abstract void run();

}
