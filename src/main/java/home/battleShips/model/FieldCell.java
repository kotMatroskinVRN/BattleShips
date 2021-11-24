package home.battleShips.model;

import home.battleShips.field.CSSpicture;
import javafx.scene.control.Button;

public class FieldCell implements Comparable<FieldCell> {

    private final int number;
    private final String letter;
    // TODO replace letter number with ShipCell

//    private CSSpicture picture = CSSpicture.SEA;
    private final Button button ;

    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;
        button = new Button();
        setStyle(CSSpicture.SEA);
    }



    public void setStyle(CSSpicture picture){
//        button = new Button();
        button.setId(picture.toString());
        button.applyCss();
    }



    public String getLetter() {
        return letter;
    }
    public int getNumber() {
        return number;
    }
    public Button getButton() {
        return button;
    }
//    public CSSpicture getPicture() {
//        return picture;
//    }

    @Override
    public int compareTo(FieldCell o) {
        return this.getLetter().compareTo( o.getLetter() );
    }

    @Override
    public String toString() {
        return " " + letter + number + " ";
    }
}