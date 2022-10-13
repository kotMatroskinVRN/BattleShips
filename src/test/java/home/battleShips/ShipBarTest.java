package home.battleShips;

import home.battleShips.field.ShipBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ShipBarTest extends Application {


    @Override
    public void start(Stage stage) {

        final BorderPane  pane = new BorderPane();
        final SplitPane splitPane = new SplitPane();
        final ShipBar playerPane = new ShipBar(true);
        final ShipBar    cpuPane = new ShipBar(false);

        final Scene scene = new Scene( pane, 700 ,150 );


        playerPane.init();
        playerPane.setId("shipsPanePlayer");

        cpuPane.init();
        cpuPane.setId("shipsPaneCPU");


        pane.getStylesheets().add("style/main.css");
        pane.getStylesheets().add("style/defaultStyle.css");

        splitPane.getItems().addAll(playerPane,cpuPane);
        pane.setTop(splitPane);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
       launch(args);
    }
}
