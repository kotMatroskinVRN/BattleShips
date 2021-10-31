package home.battleShips.field;

import javafx.scene.control.Button;

public class FieldCell implements Comparable<FieldCell> {

    private final int number;
    private final String letter;
    private CSSpicture picture = CSSpicture.SEA;
    private Button button ;

    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;
        setStyle(CSSpicture.SEA);
    }



    public void setStyle(CSSpicture picture){
        button = new Button();
        button.setId(picture.toString());
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
    public CSSpicture getPicture() {
        return picture;
    }

    @Override
    public int compareTo(FieldCell o) {
        return this.getLetter().compareTo( o.getLetter() );
    }





}
