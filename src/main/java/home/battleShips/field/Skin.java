package home.battleShips.field;

import java.net.URL;

public enum Skin {
    defaultStyle("mainStyle.css"){},
    yuraStyle("Yura.css"){},
    ;

    private String fileName;

    Skin(String fileName){
        this.fileName = fileName;
    }

    public URL getFileName() {
        return ClassLoader.getSystemResource(fileName);
//        return getClass().getResource(fileName).toExternalForm();

    }
}
