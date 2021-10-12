package home.BattleShips.Field;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class FieldCell implements Comparable<FieldCell> {


    private final ImageView SEA , HIT , MISS;

    private int number;
    private String letter;
    private ImageView imageView;


    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;
        setDefaultImage();

        SEA  = setImage("images/sea.png");
        HIT  = setImage("images/hit.png");
        MISS = setImage("images/miss.png");

    }

    public void setImageSea(){
        imageView = SEA;
    }
    public void setImageHit(){
        imageView = HIT;
    }
    public void setImageMiss(){
        imageView = MISS;
        System.out.println(letter+number+" set as miss");
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

    public ImageView getImageView() {
        return imageView;
    }





    private void setDefaultImage(){
        InputStream is = ClassLoader.getSystemResourceAsStream("images/sea.png");
        imageView = new ImageView( new Image( is ) );

    }

    private ImageView setImage(String resourceName){
        InputStream is = ClassLoader.getSystemResourceAsStream(resourceName);
        return new ImageView( new Image( is ) );
    }

}
