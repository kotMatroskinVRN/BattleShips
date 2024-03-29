package home.battleShips.model;

public enum TurnStatus {
    MISS(CssId.MISS),
    HIT(CssId.HIT),
    KILL(CssId.HIT),
    ;

    private final CssId picture;

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
