package home.battleShips.model;

import java.util.EnumSet;

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

    public static CssId getID(String string){
        for (CssId id : EnumSet.allOf(CssId.class)){
            if(string.equals(id.toString())){
                return id;
            }
        }
        return null;
    }

}
