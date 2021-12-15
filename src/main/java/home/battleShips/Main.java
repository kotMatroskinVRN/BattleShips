package home.battleShips;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main extends Application {

    private final static int FIELD_SIZE = 11 ;

    private static String[] args;
    private final static Logger log = Logger.getLogger(ClassLoader.class.getName());



    public static void main(String[] args) {


        try {
            LogManager.getLogManager().readConfiguration(
                    ClassLoader.getSystemResourceAsStream("logging.properties"));

        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }


        Main.args = args;

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        String fxml = "MainWindow.fxml";

//        if(args.length>0 && args[0].equals("-yura")) fxml = "Yura.fxml";

        FXMLLoader loader = new FXMLLoader();
        Parent content = loader.load( ClassLoader.getSystemResourceAsStream(fxml) );

        Scene scene = new Scene(content);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Морской Бой");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(
                new Image(
                        Objects.requireNonNull(
                                ClassLoader.getSystemResourceAsStream("icon/SeaBattle_32x32.PNG")
                        )
                )
        );



        primaryStage.show();
    }

    public static Logger getLog(){
        return log;
    }

    public static int getFIELD_SIZE() {
        return FIELD_SIZE;
    }
}


//TODO
// Do graphics using CSS
// in Code and FXML only markUp!!!!