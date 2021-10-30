package home.battleShips.field;

public enum CSSpicture {
    SEA(){},
    MISS(){},
    DECK(){},
    HIT(){},
    ;


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(SEA);
    }
}
