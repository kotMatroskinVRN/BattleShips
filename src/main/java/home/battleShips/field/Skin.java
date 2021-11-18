package home.battleShips.field;

import java.net.URL;

public enum Skin {
    DEFAULT("Основной"){},
    YURA("Юра"){
        @Override
        public URL getFileName() {
            return getCSS("style/Yura.css");
        }
    },
    PAPER("Бумага"){
        @Override
        public URL getFileName() {
            return getCSS("style/paper.css");
        }
    },
    ;

    private final String description;

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
