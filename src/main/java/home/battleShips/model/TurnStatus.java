package home.battleShips.model;

import home.battleShips.field.CssId;

public enum TurnStatus {
    MISS(CssId.MISS),
    HIT(CssId.HIT),
    KILL(CssId.HIT),
    ;

    private CssId picture;

    TurnStatus(CssId picture ){
        this.picture = picture;
    }

    public CssId getPicture() {
        return picture;
    }
    public String getStyleId(){
        return picture.toString();
    }
}
