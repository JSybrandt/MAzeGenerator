package controller.options;

public enum MazeOptions{
    SQUARE(0),HEX(1),TILE(2);
    private final int value;
    MazeOptions(int value){this.value = value;}
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
    public static MazeOptions fromInt(int i){
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