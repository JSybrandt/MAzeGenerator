package controller;

import controller.options.AlgorithmOptions;
import controller.options.MazeOptions;

/**
 * Created by jsybran on 10/22/2016.
 */
public class MazeDescription {
    private int numRows;
    private int numCols;
    private MazeOptions mazeOpt;
    private AlgorithmOptions algOpt;
    public MazeDescription(int numRows, int numCols, MazeOptions mazeOpt, AlgorithmOptions algOpt){
        this.numRows = numRows;
        this.numCols = numCols;
        this.mazeOpt = mazeOpt;
        this.algOpt = algOpt;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public MazeOptions getMazeOpt() {
        return mazeOpt;
    }

    public AlgorithmOptions getAlgOpt() {
        return algOpt;
    }

    @Override
    public String toString() {
        return "" + numRows + " X " + numCols + " " + mazeOpt.toString() + " Maze with " + algOpt.toString() + "'s Algorithm";
    }
}
