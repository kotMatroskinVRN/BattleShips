package home.BattleShips.Field;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class FieldCell implements Comparable<FieldCell> {

    private int number;
    private String letter;
    private ImageView imageView;

    public FieldCell(){
        letter = "";
        setDefaultImage();
    }

    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;
        setDefaultImage();
    }

    public ImageView getImageView() {
        return imageView;
    }

    private void setDefaultImage(){
        InputStream is = ClassLoader.getSystemResourceAsStream("images/sea.png");
        imageView = new ImageView( new Image( is ) );
        imageView.onMouseClickedProperty();
    }

    @Override
    public int compareTo(FieldCell o) {
        return this.getLetter().compareTo( o.getLetter() );
    }

    public String getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    public ImageView imageViewProperty(){
        return imageView;
    }

    public Integer numberProperty(){
        return number;
    }

}
