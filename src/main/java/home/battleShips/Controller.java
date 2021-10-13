package home.battleShips;

import home.battleShips.field.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.Game;
import home.battleShips.model.Ship;
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

        long start;
        double time;

//        System.out.println("\nInit Fields");
//        start = System.currentTimeMillis();
        playerField.init();
        cpuField.init();
//        time  = (System.currentTimeMillis() - start)/1000.0 ;
//        System.out.printf( "run time : %f seconds\n" , time );

//        System.out.println("\nsetup Listeners");
//        start = System.currentTimeMillis();
        setListeners(  playerField);
//        time  = (System.currentTimeMillis() - start)/1000.0 ;
//        System.out.printf( "run time : %f seconds\n" , time );

        game = new Game();

//        System.out.println("\nshow ships");
//        start = System.currentTimeMillis();
        showPlayersShips();
//        time  = (System.currentTimeMillis() - start)/1000.0 ;
//        System.out.printf( "run time : %f seconds\n" , time );
    }


    private void showPlayersShips() {
        for(Ship ship : game.getShipsCPU()){
            for(int l=1;l<FIELD_SIZE;l++) {
                for (int n = 1; n < FIELD_SIZE; n++) {
                    if( ship.hasCell(l,n) ) {
                        FieldCell cell = cpuField.getFieldData().getCells()[l][n];
                        cell.setImageDeck();
                        GridPane.setConstraints(cell.getImageView(), l, n);
                        cpuField.getChildren().add(cell.getImageView());
                    }
                }
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
        String letter = cell.getLetter();
        int number = cell.getNumber();
        System.out.println(letter+number);



        cell.setImageMiss();
        GridPane.setConstraints(cell.getImageView() , getNumberFromChar(letter), number );
        playField.getChildren().add(cell.getImageView());


    }

    private int getNumberFromChar(String string){
        char c = string.charAt(0);
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }

}
