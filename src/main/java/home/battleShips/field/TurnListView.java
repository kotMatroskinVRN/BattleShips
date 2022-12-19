package home.battleShips.field;

import home.battleShips.Language;
import home.battleShips.Translatable;
import home.battleShips.Translator;
import home.battleShips.model.FieldCell;
import home.battleShips.model.Turn;
import home.battleShips.utils.BattleShipsLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class TurnListView extends ListView<String> implements Translatable {

    private final List<Turn> turns ;
    private final ObservableList<String> data ;

    private Language language;

    public TurnListView(){

        super();
        this.language = Language.values()[0];
        Translator.addSource(this);
        turns = new ArrayList<>();
        data  = FXCollections.observableArrayList();
        setItems(data);

    }

    public void setLanguage(Language language){
        this.language = language;
    }

    public void addTurn(Turn turn){
        turns.add(turn);
        updateText(language);
        BattleShipsLogger.getLogger().printVerbose(data.toString());
        scrollTo(data.size());
    }


    @Override
    public void updateText(Language language) {
        data.clear();

        for(Turn turn : turns){
            FieldCell cell = turn.getCell();
            String letter = "letter." + cell.getLetter() ;
            String string = language.getValue(letter) + cell.getNumber();

            data.add(string);
        }
        setItems(data);

    }



    public void clearTurns(){
        turns.clear();
    }
}
