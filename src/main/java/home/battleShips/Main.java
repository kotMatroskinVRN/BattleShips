package home.battleShips;

import home.battleShips.model.FieldCell;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main extends Application implements Translatable {

    private final static int FIELD_SIZE = 11 ;
    private final static String FXML = "MainWindow.fxml";

    private static String[] args;
    private final static Logger log = Logger.getLogger(ClassLoader.class.getName());

    private static Stage stage;


    public static void main(String[] args) {


        try {
            LogManager.getLogManager().readConfiguration(
                    ClassLoader.getSystemResourceAsStream("logging.properties"));

        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }


        Main.args = args;



        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Translator.addSource(this);

        stage   = primaryStage;
        Parent content = loadFXML(Language.RUSSIAN);
        FieldCell.setLanguage(Language.RUSSIAN);


        Scene scene = new Scene(content);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(
                new Image(
                        Objects.requireNonNull(
                                ClassLoader.getSystemResourceAsStream("icon/SeaBattle_32x32.PNG")
                        )
                )
        );

        changeWindowPosition(primaryStage);


        stage.show();
    }

    @Override
    public void updateText(Language language) {
        stage.setTitle(language.getValue("title"));
    }



    public static Logger getLog(){
        return log;
    }

    public static int getFIELD_SIZE() {
        return FIELD_SIZE;
    }

    private void changeWindowPosition(Stage stage) {

        if(Arrays.asList(args).contains("-devel")){
            stage.setX(50);
            stage.setY(600);

        }

    }

    private Parent loadFXML(Language language){
        BorderPane result = null;

        FXMLLoader loader = new FXMLLoader();
        loader.setResources( language.getResourceBoundle() );


        try {
            result = loader.load( ClassLoader.getSystemResourceAsStream(FXML) );

        } catch (IOException e) {
            e.printStackTrace();
        }

        updateText(language);

        return result;

    }


}


//TODO
// Do graphics using CSS
// in Code and FXML only markUp!!!!