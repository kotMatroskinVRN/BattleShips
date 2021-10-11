package home.BattleShips;

import home.BattleShips.Field.PlayField;
import home.BattleShips.Field.grid.FieldGrid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;



public class Controller {

    @FXML
    BorderPane playerPane;
    @FXML
    BorderPane computerPane;
    @FXML
    Button test_button;
    @FXML
    BorderPane root;


    @FXML
    private void initialize(){
        PlayField playerField = new FieldGrid();
        PlayField cpuField    = new FieldGrid();

        playerField.init();
        cpuField.init();

        playerPane.setCenter((Node) playerField);
        computerPane.setCenter( (Node) cpuField );

        for(Node[] nodeLine : playerField.getCells() ) {
            for (Node node : nodeLine) {
                if(node instanceof ImageView) {
                    node.onMouseClickedProperty().set(a -> System.out.println("clicked"));
                }
            }
        }

    }

    public void turn(ActionEvent actionEvent) {
        Object object = actionEvent.getSource();
        if( object instanceof Button) {
            Button pressed = (Button) object;
        }



    }
}
