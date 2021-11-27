package home.battleShips.model;

import home.battleShips.field.CssId;

public class ShipCell {

    private final int letter;
    private final int number;
    private CssId cssId = CssId.DECK;

    public ShipCell(int letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public CssId getCssId() {
        return cssId;
    }

    public void setCssId(CssId cssId) {
        this.cssId = cssId;
    }

    public void setDeckStyle(int s , int deck){
        if(s==1){
            cssId = CssId.DECK_SINGLE;
            return;
        }
        if(deck==0)   {
            cssId = CssId.DECK_BACK;
            return;
        }
        if(deck==s-1) {
            cssId = CssId.DECK_FRONT;
            return;
        }

        cssId = CssId.DECK;

    }

    public int getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    public boolean isSamePlace(ShipCell cell){
        return letter == cell.getLetter() && number == cell.getNumber();
    }

    public boolean isNeighbour(ShipCell cell){
        return !isSamePlace(cell) &&
                Math.abs(letter - cell.getLetter() ) <= 1 &&
                Math.abs(number - cell.getNumber() ) <= 1;
    }
}
