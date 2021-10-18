package home.battleShips;

import home.battleShips.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;




public class Controller {


    @FXML
    BorderPane playerPane;
    @FXML
    BorderPane computerPane;
    @FXML
    Button newGame;


    private Game game ;

    @FXML
    private void initialize(){
        newGame();
    }


    public void newGame() {
        game = new Game(this);

        playerPane.setCenter(    game.getPlayerField());
        computerPane.setCenter(  game.getCpuField()   );
    }

    public void showVictory() {

        playerPane.setCenter( new Label("V-I-C-T-O-R-Y"));
    }
}
