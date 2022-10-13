package home.battleShips;

import home.battleShips.utils.ObservableResourceFactory;

import java.util.EnumSet;
import java.util.Locale;
import java.util.ResourceBundle;

public enum Language {
    RUSSIAN("ru"){},
    ENGLISH("en"){},
    ;

    private final ResourceBundle resourceBundle;
    private final ObservableResourceFactory resourceFactory = new ObservableResourceFactory();

    Language(String language){
        resourceBundle = ResourceBundle.getBundle("Locale", new Locale(language));
        resourceFactory.setResources(resourceBundle);
    }

    public ObservableResourceFactory getResourceFactory() {
        return resourceFactory;
    }

    public ResourceBundle getResourceBoundle(){
        return resourceBundle;
    }

    public String getValue(String key){
        return resourceBundle.getString(key);
    }

    public static Language getByResourceBundle(ResourceBundle resourceBundle){
        for(Language language : EnumSet.allOf(Language.class)){
            if(language.resourceBundle == resourceBundle){
                return language;
            }
        }
        return ENGLISH;
    }

}
