package home.battleShips;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private static String[] args;

    public static void main(String[] args) {

        Main.args = args;

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        String fxml = "MainWindow.fxml";

        if(args.length>0 && args[0].equals("-yura")) fxml = "Yura.fxml";

        FXMLLoader loader = new FXMLLoader();
        Parent content = loader.load( ClassLoader.getSystemResourceAsStream(fxml) );

        Scene scene = new Scene(content);

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
}


//TODO
// Do graphics using CSS
// in Code and FXML only markUp!!!!