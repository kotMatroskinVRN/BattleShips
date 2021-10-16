package home.battleShips.field;

import javafx.scene.image.ImageView;

public class FieldCell implements Comparable<FieldCell> {



    private final int number;
    private final String letter;
    private ImageView imageView = new ImageView(FielPicture.SEA.getIMAGE());

    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public void setImage(FielPicture picture){
        imageView = new ImageView(picture.getIMAGE());
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

    @Override
    public int compareTo(FieldCell o) {
        return this.getLetter().compareTo( o.getLetter() );
    }





}
