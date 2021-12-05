package home.battleShips.model;

public enum CssId {
    SEA(){},
    MISS(){},
    DECK(){},
    DECK_FRONT(){},
    DECK_BACK(){},
    DECK_V(){},
    DECK_FRONT_V(){},
    DECK_BACK_V(){},
    DECK_SINGLE(){},
    HIT(DECK){},
    HIT_FRONT(DECK_BACK){},
    HIT_BACK(DECK_FRONT){},
    HIT_SINGLE(DECK_SINGLE){},
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

    public CssId getAfterKill() {
        return afterKill;
    }

    public static void main(String[] args) {
        System.out.println(SEA);
    }
}
