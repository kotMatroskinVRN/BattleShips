package home.battleShips.field;

import javafx.scene.image.ImageView;

public class FieldCell implements Comparable<FieldCell> {

    private final int number;
    private final String letter;
    private CSSpicture picture = CSSpicture.SEA;
//    private ImageView imageView = new ImageView(FieldPicture.SEA.getIMAGE());
    private ImageView imageView = new ImageView();

    public FieldCell(String letter, int number) {
        this.letter = letter;
        this.number = number;
    }

//    public void setImage(FieldPicture picture){
//        imageView = new ImageView(picture.getIMAGE());
//    }

    public void setStyle(CSSpicture picture){
        imageView = new ImageView();
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        imageView.setId(picture.toString());
//        imageView.setStyle("    -fx-pref-width:  30px; -fx-pref-height: 30px;");
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
    public CSSpicture getPicture() {
        return picture;
    }

    @Override
    public int compareTo(FieldCell o) {
        return this.getLetter().compareTo( o.getLetter() );
    }





}
