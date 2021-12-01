package home.battleShips.model;

import javafx.scene.control.Button;

public class FieldCell implements Comparable<FieldCell> {

    private final int number;
    private final String letter;
    // TODO replace letter number with ShipCell

//    private Button button ;
    private CssId cssId ;

    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;

//         button = new Button();

        setStyle(CssId.SEA);
    }



//    public void setStyle(CssId picture){
//        button.setId(picture.toString());
//        button.applyCss();
//    }
    public void setStyle(CssId picture){
        cssId = picture;
    }

    public CssId getCssId() {
        return cssId;
    }

    public String getLetter() {
        return letter;
    }
    public int getNumber() {
        return number;
    }
//    public Button getButton() {
//        return button;
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
