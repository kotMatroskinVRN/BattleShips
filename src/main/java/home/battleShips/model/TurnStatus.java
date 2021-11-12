package home.battleShips.model;

import home.battleShips.field.CSSpicture;

public enum TurnStatus {
    MISS(CSSpicture.MISS),
    HIT(CSSpicture.HIT),
    KILL(CSSpicture.HIT),
    ;

    private CSSpicture picture;

    TurnStatus(CSSpicture picture ){
        this.picture = picture;
    }

    public CSSpicture getPicture() {
        return picture;
    }
    public String getStyleId(){
        return picture.toString();
    }
}
