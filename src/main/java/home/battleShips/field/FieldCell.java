package home.battleShips.field;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class FieldCell implements Comparable<FieldCell> {


    public static final Image  SEA  = setImage("images/sea.png");
    public static final Image  HIT  = setImage("images/hit.png");
    public static final Image  MISS = setImage("images/miss.png");
    public static final Image  DECK = setImage("images/deck.png");

    private int number;
    private String letter;
    private ImageView imageView = new ImageView(SEA);


    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;


    }

    public void setImageSea(){
        imageView = new ImageView(SEA);
    }
    public void setImageDeck(){
        imageView = new ImageView(DECK);
    }
    public void setImageHit(){
        imageView = new ImageView(HIT);
    }
    public void setImageMiss(){
        imageView = new ImageView(MISS);
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

    private static Image setImage(String resourceName){
        InputStream is = ClassLoader.getSystemResourceAsStream(resourceName);
        return  new Image( is ) ;
    }

}
