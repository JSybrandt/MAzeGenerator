package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.RoomViewerTEST;

/**
 * Justin Sybrandt
 *
 * The application controller is responsible for initializing the views and controllers.
 * The application controller will also respond to user events passed back from the view.
 *
 * The application controller DOES NOT directly handle either the views or controllers.
 */
public class ApplicationController {
    public ApplicationController(BorderPane root){
        createModel();
        createView(root);
    }

    private void createView(BorderPane root){
        root.setCenter(new RoomViewerTEST());
        root.setTop(new Label("This is a test!"));
    }
    private void createModel(){

    }
}
