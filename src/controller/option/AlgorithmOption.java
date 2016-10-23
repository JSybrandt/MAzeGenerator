/*
* Justin Sybrandt
*
* Description:
* This class defines the option values which the user can
* select when choosing their algorithm from the dropdown.
*
* This class is used by the SettingsController to build
* a model based on user specifications.
*
* Important Values:
* PRIM
* KRUSKAL
* ELLER
*
* */


package controller.option;

public enum AlgorithmOption {
    PRIM(0),KRUSKAL(1),ELLER(2);
    private final int value;
    AlgorithmOption(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (value){
            case 0:
                return "Prim";
            case 1:
                return "Kruskal";
            case 2:
                return "Eller";
        }
        return super.toString();
    }
    public static AlgorithmOption fromInt(int i){
        switch (i){
            case 0:
                return PRIM;
            case 1:
                return KRUSKAL;
            case 2:
                return ELLER;
        }
        return null;
    }
}