package home.battleShips.model;

import javafx.scene.control.Button;

public class FieldCell implements Comparable<FieldCell> {

    private final int number;
    private final int letter;
    // TODO replace letter number with ShipCell

//    private Button button ;
    private CssId cssId ;

    public FieldCell(int letter, int number) {
        this.letter = letter;
        this.number = number;

        setStyle(CssId.SEA);
    }

    public void setDeckStyle(int s , int deck , boolean v){
        if(s==1){
            cssId = CssId.DECK_SINGLE;
            return ;
        }
        if(deck==0)   {
            cssId = v?CssId.DECK_FRONT_V:CssId.DECK_FRONT;
            return;
        }
        if(deck==s-1) {
            cssId = v?CssId.DECK_BACK_V:CssId.DECK_BACK;
            return;
        }

        cssId = v?CssId.DECK_V:CssId.DECK;

    }

    public boolean isSamePlace(FieldCell cell){
        return letter == cell.getLetter() && number == cell.getNumber();
    }

    public boolean isNeighbour(FieldCell cell){
        return !isSamePlace(cell) &&
                Math.abs(letter - cell.getLetter() ) <= 1 &&
                Math.abs(number - cell.getNumber() ) <= 1;
    }

    public void setStyle(CssId picture){
        cssId = picture;
    }

    public CssId getCssId() {
        return cssId;
    }
    public int getLetter() {
        return letter;
    }
    public int getNumber() {
        return number;
    }

    public String covertLetter(){
        char result =  letter==10? 'К' : (char) ('А' + letter-1);
        return String.valueOf(result);
    }

    @Override
    public int compareTo(FieldCell o) {
        if(letter == o.getLetter()) return 0;

        return  letter>o.getLetter()?1:0 ;
    }

    @Override
    public String toString() {
        return " " + covertLetter() + number + " ";
    }
}
