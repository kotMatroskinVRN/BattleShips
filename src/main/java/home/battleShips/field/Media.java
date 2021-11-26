package home.battleShips.field;

import java.net.URL;

public enum Media {

    VICTORY("images/win.mp4"){},
    LOSS("images/loss.mp4"){},
    ;

    private final String mediaUrl;

    Media(String png){
        mediaUrl = png;
    }

    public URL getMediaURL() {
        return ClassLoader.getSystemResource(mediaUrl);
    }



}
