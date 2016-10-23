/*
* Justin Sybrandt
*
* Description:
* The settings controller is in charge of populating the settingPane's comboboxes as well as
* retrieving information from that pane when the user wants to add a new maze.
*
* The SettingsController needs a reference to the ApplicationController to alert it when the user wants
* to add a new maze.
*
* Important Values:
* ALG_OPTS - A mapping between AlgorithmOption and human readable text used for display.
* MAZE_OPTS - A mapping between MazeOption and human readable text used for display.
*
* */

package controller;

import Util.BiMap;
import controller.option.AlgorithmOption;
import controller.option.MazeOption;
import view.SettingsPane;

public class SettingsController extends Controller {

    private static final BiMap<Integer,String> ALG_OPTS;
    private static final BiMap<Integer,String> MAZE_OPTS;

    SettingsPane settingsPane;

    //Options
    static{
        BiMap<Integer,String> algOpt2Str = new BiMap<>();
        algOpt2Str.put(AlgorithmOption.PRIM.getValue(), "Prim's Spanning Tree");
        algOpt2Str.put(AlgorithmOption.KRUSKAL.getValue(), "Kruskal's Spanning Tree");
        algOpt2Str.put(AlgorithmOption.ELLER.getValue(), "Eller's Maze Generator");
        ALG_OPTS = algOpt2Str;

        BiMap<Integer,String> mazeOpt2Str = new BiMap<>();
        mazeOpt2Str.put(MazeOption.SQUARE.getValue(), "Square");
        mazeOpt2Str.put(MazeOption.HEX.getValue(), "Hexagon");
        mazeOpt2Str.put(MazeOption.TILE.getValue(), "Tessellation");
        MAZE_OPTS = mazeOpt2Str;
    }

    private static final String ALG_OPT_NAME = "Algorithm";
    private static final String MAZE_OPT_NAME = "Maze Type";

    public SettingsController(SettingsPane pane, ApplicationController appController) {
        super(pane);
        settingsPane = pane;
        settingsPane.getAddButton().setOnAction(evt -> appController.addMaze(generateMazeDescription()));
    }

    @Override
    public void run() {
        settingsPane.addSelector(ALG_OPT_NAME,ALG_OPTS);
        settingsPane.addSelector(MAZE_OPT_NAME,MAZE_OPTS);
    }

    private MazeDescription generateMazeDescription(){
        AlgorithmOption algOpt = AlgorithmOption.fromInt(settingsPane.getComboBoxValue(ALG_OPT_NAME));
        MazeOption mazeOpt = MazeOption.fromInt(settingsPane.getComboBoxValue(MAZE_OPT_NAME));
        int rows = settingsPane.getRowValue();
        int cols = settingsPane.getColValue();
        return new MazeDescription(rows,cols,mazeOpt,algOpt);
    }
}
