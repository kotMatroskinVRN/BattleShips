package home.battleShips.field;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.fxml.FXMLLoader.*;

public class ModalNewGameFXML {

    private static Parent modalRoot;
    private static FXMLLoader loader;

    private Stage modalStage ;


    static {

        try { String fxml = "NewGameModal.fxml";
            loader = new FXMLLoader();

            modalRoot = loader.load( ClassLoader.getSystemResourceAsStream(fxml) );

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    public void initModalNewGame(Parent parent) {



            modalStage = new Stage();


            modalStage.setResizable(false);
            modalStage.setTitle("Новая игра");
            modalStage.setScene(new Scene(modalRoot));
//            modalStage.initModality(Modality.WINDOW_MODAL);
//            modalStage.initOwner( parent.getScene().getWindow() );

            modalStage.show();
    }


    public static void main(String[] args) {




    }
}
