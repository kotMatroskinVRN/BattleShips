package home.battleShips;

import java.util.ArrayList;
import java.util.List;

public class Translator {

    private static  List<Translatable> items = new ArrayList<>(

    );

    public static void addSource(Translatable translatable) {
        items.add(translatable);
    }


    public static void updateText(Language language) {
        for(Translatable item : items){
            item.updateText(language);
        }
    }
}
