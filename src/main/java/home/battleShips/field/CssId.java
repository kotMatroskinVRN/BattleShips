package home.battleShips.field;

public enum CssId {
    SEA(){},
    MISS(){},
    DECK(){},
    DECK_FRONT(){},
    DECK_BACK(){},
    DECK_SINGLE(){},
    HIT(){},
    HIT_FRONT(){},
    HIT_BACK(){},
    HIT_SINGLE(){},
    ;


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(SEA);
    }
}
