package home.battleShips.model;

public enum ShipClass {

    CARRIER(4){},
    BATTLESHIP(3){},
    DESTROYER(2){},
    TORPEDOBOAT(1){},
    ;

    private int size;

    ShipClass(int size) {
        this.size = size;
    }

    public int getSize(){
        return size;
    }

}
