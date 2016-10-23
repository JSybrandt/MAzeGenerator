package controller.options;

public enum AlgorithmOptions{
    PRIM(0),KRUSKAL(1),ELLER(2);
    private final int value;
    AlgorithmOptions(int value) {
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
    public static AlgorithmOptions fromInt(int i){
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