package home.battleShips.field;

import java.net.URL;

public enum Media {

    VICTORY("images/win.mp4"){},
    LOSS("images/loss.mp4"){},
    ;

    private final String imagePath;

    Media(String png){
        imagePath = png;
    }

    public URL getImagePath() {
        return ClassLoader.getSystemResource(imagePath);
    }



}
