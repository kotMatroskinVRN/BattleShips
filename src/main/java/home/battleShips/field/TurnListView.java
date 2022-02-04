package home.battleShips.field;

import home.battleShips.Language;
import home.battleShips.Translatable;
import home.battleShips.Translator;
import home.battleShips.model.FieldCell;
import home.battleShips.model.Turn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class TurnListView extends ListView<String> implements Translatable {

    List<Turn> turns ;
    ObservableList<String> data ;

    Language language;

    public TurnListView(){
        super();
        Translator.addSource(this);
        turns = new ArrayList<>();
        data  = FXCollections.observableArrayList();
        data.add("New");
        setItems(data);
    }

    public TurnListView(Language language){
        super();
        Translator.addSource(this);
        turns = FXCollections.observableArrayList();
        data  = FXCollections.observableArrayList();
        data.add("New");
        setItems(data);
        this.language = language;
    }


    public void setLanguage(Language language){
        this.language = language;
    }

    public void addTurn(Turn turn){
        turns.add(turn);
        updateText(language);
        System.out.println(data);
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


//    private void
}
