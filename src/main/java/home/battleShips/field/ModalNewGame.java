package home.battleShips.field;

import home.battleShips.Language;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;



public class ModalNewGame {

    private final int WIDTH  = 350;
    private final int HEIGHT = 150;

    private final Button okButton ;
    private final Stage window;

    public ModalNewGame() {
        window = new Stage();
        okButton = new Button();
    }

    public void initModalNewGame(Parent parent , Language language) {

        final BorderPane pane = new BorderPane();
        final FlowPane bottomPane = new FlowPane();

        final Scene scene = new Scene( pane, WIDTH ,HEIGHT );

        Window parentWindow = parent.getScene().getWindow();
        double centerX = parentWindow.getX();
        double centerY = parentWindow.getY();
        double parentWidth   = parentWindow.getWidth();
        double parentHeight  = parentWindow.getHeight();

        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(parentWindow);
        window.setResizable(false);
        window.setTitle(language.getValue("newGame.title"));
        window.setX(centerX+(parentWidth-WIDTH)/2);
        window.setY(centerY+(parentHeight-HEIGHT)/2);

        window.setScene(scene);

        okButton.setText(language.getValue("newGame.ok"));
        okButton.setDefaultButton(true);


        Button cancelButton = new Button(language.getValue("newGame.cancel"));
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction( (this::closeWindow));

        bottomPane.setAlignment(Pos.BASELINE_RIGHT);
        bottomPane.getChildren().addAll(okButton,cancelButton);

        final String test = language.getValue("newGame.message");
        final Label label = new Label(test);

        pane.setBottom(bottomPane);
        pane.setCenter(label);
        pane.getStylesheets().add("style/modal_new_game.css");
        pane.applyCss();



    }


    public void showWindow(){
        window.showAndWait();
    }

    public Button getOkButton() {
        return okButton;
    }
    public void closeWindow(ActionEvent actionEvent){
        window.close();
    }

}
