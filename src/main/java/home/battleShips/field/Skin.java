package home.battleShips.field;

import java.net.URL;

public enum Skin {
    defaultStyle("Основной"){},
    yuraStyle("Юра"){
        @Override
        public URL getFileName() {
            return getCSS("style/Yura.css");
        }
    },
    paperStyle("Бумага"){
        @Override
        public URL getFileName() {
            return getCSS("style/paper.css");
        }
    },
    ;

    private String description;

    Skin(String description){
        this.description = description;
    }

    public URL getFileName() {
        return getCSS("style/mainStyle.css");
    }

    @Override
    public String toString() {
        return this.description;
    }

    URL getCSS(String fileName){
        return ClassLoader.getSystemResource(fileName);
    }
}
