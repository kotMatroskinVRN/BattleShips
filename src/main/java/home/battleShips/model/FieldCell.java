package home.battleShips.model;


import home.battleShips.Language;
import home.battleShips.Main;

import java.util.Objects;

public class FieldCell implements Comparable<FieldCell>  {

    private final int number;
    private final int letter;

    private CssId cssId ;

    private static Language language;

    public FieldCell(int letter, int number) {
        if(letter<1||letter> Main.getFIELD_SIZE()-1||
                number<1 || number>Main.getFIELD_SIZE()-1) {
            throw new NullPointerException();
        }

        this.letter = letter;
        this.number = number;

        setStyle(CssId.SEA);

    }

    public void setDeckStyle(int s , int deck ){
        if(s==1){
            cssId = CssId.DECK_SINGLE;
            return ;
        }
        if(deck==0)   {
            cssId = CssId.DECK_FRONT;
            return;
        }
        if(deck==s-1) {
            cssId = CssId.DECK_BACK;
            return;
        }

        cssId = CssId.DECK;

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

//    public String covertLetter(){
//        return language.getValue( "letter." + letter );
//    }

    @Override
    public int compareTo(FieldCell o) {
        if(letter == o.getLetter()) return 0;

        return  letter>o.getLetter()?1:0 ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldCell fieldCell = (FieldCell) o;
        return number == fieldCell.number && letter == fieldCell.letter ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, letter);
    }

    @Override
    public String toString() {
        return " " + covertLetter() + number + " ";
    }

    public static void setLanguage(Language language) {
        FieldCell.language = language;
    }

//    @Override
//    public void updateText(Language language) {
//        setLanguage(language);
//    }
}
