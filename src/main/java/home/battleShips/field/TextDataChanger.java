package home.battleShips.field;

import home.battleShips.Language;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class TextDataChanger {

    private Language language;


    private Scene scene;
    private BorderPane root;

    public TextDataChanger(BorderPane root ){
        this.root = root;
    }

    public void applyLanguage(Language language){
        this.language = language;

        FXMLLoader loader = new FXMLLoader();
        loader.setResources( language.getResourceBoundle() );

//        root.set

    }


}
