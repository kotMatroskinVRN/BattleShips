package home.BattleShips;

import home.BattleShips.Field.FieldCell;
import home.BattleShips.Field.PlayField;
import home.BattleShips.Field.grid.FieldGrid;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Controller {

    private final int FIELD_SIZE = 11 ;

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

        setListeners( (FieldGrid) playerField);

    }





    private void setListeners(FieldGrid playerField) {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
                cell.getImageView().onMouseClickedProperty().set( ae -> turn(playerField ,cell) );

            }
        }
    }

    private void turn(FieldGrid playField , FieldCell cell) {
        String letter = cell.getLetter();
        int number = cell.getNumber();
        System.out.println(letter+number);



        cell.setImageMiss();
        playField.setConstraints(cell.getImageView() , getNumberFromChar(letter), number );
        playField.getChildren().add(cell.getImageView());


    }

    private int getNumberFromChar(String string){
        char c = string.charAt(0);
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }

}
