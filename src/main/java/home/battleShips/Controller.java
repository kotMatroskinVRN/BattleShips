package home.battleShips;

import home.battleShips.field.FielPicture;
import home.battleShips.field.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.Game;
import home.battleShips.model.Ship;
import home.battleShips.model.ShipCell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


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

    private FieldGrid playerField;
    private FieldGrid cpuField;
    private Game game ;

    @FXML
    private void initialize(){

        playerField = new FieldGrid();
        cpuField    = new FieldGrid();



        playerPane.setCenter( playerField);
        computerPane.setCenter(  cpuField );

        newGame();
    }


    public void newGame() {

        playerField.init();
        cpuField.init();
        setListeners(  playerField);

        game = new Game();

        showPlayersShips();
    }


    private void showPlayersShips() {
        for(Ship ship : game.getShipsCPU()){

            for(ShipCell shipCell : ship.getShipCellList()){
                int l = shipCell.getLetter();
                int n = shipCell.getNumber();
                FieldCell cell = cpuField.getFieldData().getCells()[l][n];
                cell.setImage(FielPicture.DECK);
                GridPane.setConstraints(cell.getImageView(), l, n);
                cpuField.getChildren().add(cell.getImageView());
            }
        }
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
        int letter = getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println(cell.getLetter()+number);




        boolean hit = false;
        for(Ship ship : game.getShipsPLAYER()){
            if( ship.hasCell(letter,number )){
                hit = true;
                ship.addHit(letter,number);
                setImageToGridCell(playField, cell, FielPicture.HIT);
                if(ship.isKilled()){
                    surroundShip(playField ,ship);
                    game.killShip();
                }
                break;
            }
        }

        if(!hit){
            setImageToGridCell(playField, cell, FielPicture.MISS);
        }

    }

    private void surroundShip(FieldGrid playField ,Ship ship) {
        for(ShipCell shipCell : ship.getShipCellList()){
            for(int dl=-1;dl<=1;dl++) {
                for (int dn = -1; dn <= 1; dn++) {
                    int letter = shipCell.getLetter()+dl;
                    int number = shipCell.getNumber()+dn;
                    try {

                        if(!ship.hasCell(letter,number)){
                            FieldCell cell = playField.getFieldData().getCells()[letter][number];
                            setImageToGridCell(playField,cell,FielPicture.MISS);
                        }
                    } catch (NullPointerException  | ArrayIndexOutOfBoundsException ignored){}

                }
            }
        }
    }

    private void setImageToGridCell(FieldGrid playField, FieldCell cell, FielPicture picture) {
        int letter = getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        cell.setImage(picture);
        GridPane.setConstraints(cell.getImageView(), letter, number);
        playField.getChildren().add(cell.getImageView());


    }

    private int getNumberFromChar(String string){
        char c = string.charAt(0);
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }

}
