package home.battleShips.field;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Objects;

public enum FielPicture {

    SEA("images/sea.png"){},
    HIT("images/hit.png"){},
    MISS("images/miss.png"){},
    DECK("images/deck.png"){},
    ;

    private final Image IMAGE;

    FielPicture(String png){
        IMAGE = setImage(png);
    }

    public Image getIMAGE() {
        return IMAGE;
    }

    private static Image setImage(String resourceName){
        InputStream is = ClassLoader.getSystemResourceAsStream(resourceName);
        return  new Image(Objects.requireNonNull(is)) ;
    }



}
