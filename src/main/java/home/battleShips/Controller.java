package home.battleShips;

import home.battleShips.field.FieldPicture;
import home.battleShips.field.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.Game;
import home.battleShips.model.Ship;
import home.battleShips.model.ShipCell;
import home.battleShips.utils.StaticUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


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
        game = new Game();

        playerPane.setCenter(    game.getPlayerField());
        computerPane.setCenter(  game.getCpuField()   );
    }

}
