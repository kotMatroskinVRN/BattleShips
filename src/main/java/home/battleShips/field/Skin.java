package home.battleShips.field;

import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;

public enum Skin {
    DEFAULT("skin.default"){},
    YURA("skin.yura"){
        @Override
        public URL getFileName() {
            return getCSS("style/Yura.css");
        }
    },
    PAPER("skin.paper"){
        @Override
        public URL getFileName() {
            return getCSS("style/paper.css");
        }
    },
    ;

    private String description;
    private final String key;

    Skin(String name){
        key = name;
    }

    public URL getFileName() {
        return getCSS("style/defaultStyle.css");
    }

    public static URL getMainCSS(){
        return ClassLoader.getSystemResource("style/main.css");
    }

    public static void updateDescription(ResourceBundle resourceBundle){
        for(Skin skin : EnumSet.allOf(Skin.class)){
//            final String description = skin.description;
            skin.description = resourceBundle.getString(skin.key);
        }
    }

    public static Skin getSkin(String description){

        for(Skin skin : EnumSet.allOf(Skin.class)){
            if(description.equals(skin.description)) return skin;
        }
        return null;
    }

    @Override
    public String toString() {
        return description;
    }

    URL getCSS(String fileName){
        return ClassLoader.getSystemResource(fileName);
    }
}
