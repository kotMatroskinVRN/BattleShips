package home.battleShips.field;

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


public class ModalNewGame {

    private final Button okButton ;
    private final Stage window;

    public ModalNewGame() {
        window = new Stage();
        okButton = new Button("Да");
    }

    public void initModalNewGame(Parent parent) {

        final BorderPane pane = new BorderPane();
        final FlowPane bottomPane = new FlowPane();

        final Scene scene = new Scene( pane, 300 ,150 );

        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(parent.getScene().getWindow());
        window.setResizable(false);
        window.setTitle("Новая игра");
        window.setScene(scene);

        okButton.setDefaultButton(true);


        Button cancelButton = new Button("Нет");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction( (this::closeWindow));

        bottomPane.setAlignment(Pos.BASELINE_RIGHT);
        bottomPane.getChildren().addAll(okButton,cancelButton);

        final String test = "Начать новую игру?\nДанные этой игры будут утеряны";
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
