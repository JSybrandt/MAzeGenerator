/*
* Justin Sybrandt
*
* Description:
* This class provides a common way to describe mazes. A maze is some height, width, shape,
* and is generated with some algorithm.
*
* Primarily, this class is used for the SettingsController to send information to the ApplicationController.
*
* Lastly, this class provides a way to display a maze as text, used to fill the header of a MazeContainer.
*
* Important Values:
* numRows
* numCols
* mazeOpt
* algOpt
*
* */

package controller;

import controller.option.AlgorithmOption;
import controller.option.MazeOption;

public class MazeDescription {
    private int numRows;
    private int numCols;
    private MazeOption mazeOpt;
    private AlgorithmOption algOpt;
    public MazeDescription(int numRows, int numCols, MazeOption mazeOpt, AlgorithmOption algOpt){
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

    public MazeOption getMazeOpt() {
        return mazeOpt;
    }

    public AlgorithmOption getAlgOpt() {
        return algOpt;
    }

    @Override
    public String toString() {
        return "" + numRows + " X " + numCols + " " + mazeOpt.toString() + " Maze with " + algOpt.toString() + "'s Algorithm";
    }
}
