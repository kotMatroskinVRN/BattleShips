package home.battleShips.field;

import javafx.scene.image.Image;

import java.io.InputStream;

public enum FielPicture {

    SEA("images/sea.png"){},
    HIT("images/hit.png"){},
    MISS("images/miss.png"){},
    DECK("images/deck.png"){},
    ;

    Image image;

    FielPicture(String png){
        image = setImage(png);
    }

    public Image getImage() {
        return image;
    }

    private static Image setImage(String resourceName){
        InputStream is = ClassLoader.getSystemResourceAsStream(resourceName);
        return  new Image( is ) ;
    }



}
