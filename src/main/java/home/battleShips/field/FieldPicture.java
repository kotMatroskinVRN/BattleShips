package home.battleShips.field;

import javafx.embed.swing.SwingNode;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public enum FieldPicture {

//    SEA("images/sea.png"){},
//    HIT("images/hit.png"){},
//    MISS("images/miss.png"){},
//    DECK("images/deck.png"){},
    VICTORY("images/win.mp4"){},
    LOSS("images/loss.mp4"){},

    ;

    private final Image IMAGE;
    private final String imagePath;

    FieldPicture(String png){
        IMAGE = setImage(png);
        imagePath = png;
    }

    public Image getIMAGE() {
        return IMAGE;
    }

    public URL getImagePath() {
        return ClassLoader.getSystemResource(imagePath);
    }

    private static Image setImage(String resourceName){
        InputStream is = ClassLoader.getSystemResourceAsStream(resourceName);
        return  new Image(Objects.requireNonNull(is)) ;


    }


}
