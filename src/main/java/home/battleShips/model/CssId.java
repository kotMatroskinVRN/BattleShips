package home.battleShips.model;

public enum CssId {
    SEA(){},
    MISS(){},
    HIT(){},
    HIT_DECK(){},
    HIT_FRONT(){},
    HIT_BACK(){},
    HIT_SINGLE(){},
    DECK(HIT_DECK){},
    DECK_FRONT(HIT_FRONT){},
    DECK_BACK(HIT_BACK){},
    DECK_SINGLE(HIT_SINGLE){},


    ;

    CssId afterKill;

    CssId(){
        afterKill=this;
    }

    CssId(CssId id){
        afterKill = id;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public CssId getKilled() {
        return afterKill;
    }

    public static void main(String[] args) {
        System.out.println(SEA);
    }
}
