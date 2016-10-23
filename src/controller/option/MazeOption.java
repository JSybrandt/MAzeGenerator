/*
* Justin Sybrandt
*
* Description:
* This class defines the option values which the user can
* select when choosing their maze type from the dropdown.
*
* This class is used by the SettingsController to build
* a model based on user specifications.
*
* Important Values:
* SQUARE
* HEX
* TILE
*
* */

package controller.option;

public enum MazeOption {
    SQUARE(0),HEX(1),TILE(2);
    private final int value;
    MazeOption(int value){this.value = value;}
    public int getValue(){return value;}
    @Override
    public String toString() {
        switch (value){
            case 0:
                return "Square";
            case 1:
                return "Hexagon";
            case 2:
                return "Tessellation";
        }
        return super.toString();
    }
    public static MazeOption fromInt(int i){
        switch (i){
            case 0:
                return SQUARE;
            case 1:
                return HEX;
            case 2:
                return TILE;
        }
        return null;
    }
}