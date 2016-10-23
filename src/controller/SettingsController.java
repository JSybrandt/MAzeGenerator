package controller;

import Util.BiMap;
import controller.options.AlgorithmOptions;
import controller.options.MazeOptions;
import javafx.scene.control.ComboBox;
import model.Maze;
import view.SettingsPane;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jsybran on 10/22/2016.
 */
public class SettingsController extends Controller {

    private static final BiMap<Integer,String> ALG_OPTS;
    private static final BiMap<Integer,String> MAZE_OPTS;

    SettingsPane settingsPane;

    //Options
    static{
        BiMap<Integer,String> algOpt2Str = new BiMap<>();
        algOpt2Str.put(AlgorithmOptions.PRIM.getValue(), "Prim's Spanning Tree");
        algOpt2Str.put(AlgorithmOptions.KRUSKAL.getValue(), "Kruskal's Spanning Tree");
        algOpt2Str.put(AlgorithmOptions.ELLER.getValue(), "Eller's Maze Generator");
        ALG_OPTS = algOpt2Str;

        BiMap<Integer,String> mazeOpt2Str = new BiMap<>();
        mazeOpt2Str.put(MazeOptions.SQUARE.getValue(), "Square");
        mazeOpt2Str.put(MazeOptions.HEX.getValue(), "Hexagon");
        mazeOpt2Str.put(MazeOptions.TILE.getValue(), "Tessellation");
        MAZE_OPTS = mazeOpt2Str;
    }

    private static final String ALG_OPT_NAME = "Algorithm";
    private static final String MAZE_OPT_NAME = "Maze Type";

    private ComboBox<String> algOptCBox;
    private ComboBox<String> mazeOptCBox;

    public SettingsController(SettingsPane pane, ApplicationController appController) {
        super(pane);
        settingsPane = pane;
        settingsPane.getAddButton().setOnAction(evt -> appController.addMaze(generateMazeDescription()));
    }

    @Override
    public void run() {
        algOptCBox = settingsPane.addSelector(ALG_OPT_NAME,ALG_OPTS);
        mazeOptCBox = settingsPane.addSelector(MAZE_OPT_NAME,MAZE_OPTS);
    }

    private MazeDescription generateMazeDescription(){
        AlgorithmOptions algOpt = AlgorithmOptions.fromInt(settingsPane.getComboBoxValue(ALG_OPT_NAME));
        MazeOptions mazeOpt = MazeOptions.fromInt(settingsPane.getComboBoxValue(MAZE_OPT_NAME));
        int rows = settingsPane.getRowValue();
        int cols = settingsPane.getColValue();
        return new MazeDescription(rows,cols,mazeOpt,algOpt);
    }
}
