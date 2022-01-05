package home.battleShips;

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

public class Main extends Application {

    private final static int FIELD_SIZE = 11 ;
    private final static String FXML = "MainWindow.fxml";

    private static String[] args;
    private final static Logger log = Logger.getLogger(ClassLoader.class.getName());

    private static Parent content ;
    private static Scene scene;
    private static Stage stage;
    private static FXMLLoader loader;


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
    public void start(Stage primaryStage) throws Exception {



//        loader = new FXMLLoader();
//        Language language = Language.RUSSIAN;
//        loader.setResources( language.getResourceBoundle() );

//        content = loader.load( ClassLoader.getSystemResourceAsStream(fxml) );
        stage = primaryStage;
        content = loadFXML(Language.RUSSIAN);



        scene = new Scene(content);

        stage.setResizable(false);
//        primaryStage.setTitle(language.getValue("title"));
        stage.setScene(scene);
        stage.getIcons().add(
                new Image(
                        Objects.requireNonNull(
                                ClassLoader.getSystemResourceAsStream("icon/SeaBattle_32x32.PNG")
                        )
                )
        );

        changeWindowPosition(primaryStage);



//        primaryStage.show();
        stage.show();
    }

    public static void applyLanguage(Language language){

        ((BorderPane) content).getChildren().addAll( ((BorderPane) loadFXML(language)).getChildren() );
    }

    private static Parent loadFXML(Language language){
        BorderPane result = null;

        loader = new FXMLLoader();
        loader.setResources( language.getResourceBoundle() );


        try {
            result = loader.load( ClassLoader.getSystemResourceAsStream(FXML) );

        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle(language.getValue("title"));

        return result;

    }

    public static Logger getLog(){
        return log;
    }

    public static int getFIELD_SIZE() {
        return FIELD_SIZE;
    }

    public static String getFxml(){
        return FXML;
    }

    public static FXMLLoader getLoader(){
        return loader;
    }

    private void changeWindowPosition(Stage stage) {

        if(Arrays.asList(args).contains("-devel")){
            stage.setX(50);
            stage.setY(600);

        }

    }
}


//TODO
// Do graphics using CSS
// in Code and FXML only markUp!!!!