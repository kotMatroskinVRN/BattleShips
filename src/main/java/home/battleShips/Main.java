package home.battleShips;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent content = loader.load( ClassLoader.getSystemResourceAsStream("MainWindow.fxml") );

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
