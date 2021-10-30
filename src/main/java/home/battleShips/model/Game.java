package home.battleShips.model;

import home.battleShips.Controller;
import home.battleShips.field.CSSpicture;
import home.battleShips.field.FieldCell;
import home.battleShips.field.FieldPicture;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.utils.StaticUtils;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

import java.util.Date;

public class Game {

    private final int FIELD_SIZE = 11 ;
    static final int FS        = 12 ;
//    private final Ship[] shipsCPU      ;
//    private final Ship[] shipsPLAYER   ;
//    private final Stack<Turn> playersTurns;
//    private final Stack<Turn> cpusTurns;


    private final FieldGrid playerField;
    private final FieldGrid cpuField;
    private final  Controller controller;

    public Game(Controller controller) {
        this.controller = controller;

//        playersTurns = new Stack<>();
//        cpusTurns    = new Stack<>();

        playerField = new FieldGrid();
        cpuField    = new FieldGrid();

        playerField.init();
        cpuField.init();

//        shipsCPU    = Ship.randomSetOfShips() ;
//        shipsPLAYER = Ship.randomSetOfShips() ;

        showPlayersShips();

        System.out.println(new Date());

        setListeners();

    }


    public FieldGrid getPlayerField() {
        return playerField;
    }

    public FieldGrid getCpuField() {
        return cpuField;
    }
//    public Ship[] getShipsCPU() {
//        return shipsCPU;
//    }
//    public Ship[] getShipsPLAYER() {
//        return shipsPLAYER;
//    }

    private void turn( FieldCell cell) {

        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println(cell.getLetter()+number);


        Turn turn = new Turn(cell);

        for(Ship ship : playerField.getFieldData().getShips()){
            if( ship.hasCell(letter,number )){
                turn.setStatus(TurnStatus.HIT);
                ship.addHit(letter,number);

                playerField.setImageToGridCell( cell, CSSpicture.HIT);
//                setHit(playerField,cell);

                if(ship.isKilled()){
                    turn.setStatus(TurnStatus.KILL);
                    killShip(ship,playerField);
                }
                break;
            }
        }


        if(turn.getStatus()==TurnStatus.MISS){
            playerField.setImageToGridCell( cell, CSSpicture.MISS);
            counterAction();
        }




    }

    private void counterAction() {
        Turn turn = new Turn(cpuField);

        FieldCell cell = turn.getCell();
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println("cpu:" + cell.getLetter()+number);

        for(Ship ship : cpuField.getFieldData().getShips()){
            if( ship.hasCell(letter,number )){
                turn.setStatus(TurnStatus.HIT);
                ship.addHit(letter,number);

                cpuField.setImageToGridCell( cell, CSSpicture.HIT);
//                setHit(cpuField,cell);

                if(ship.isKilled()){
                    turn.setStatus(TurnStatus.KILL);
                    killShip(ship , cpuField);
                }
                break;
            }
        }

        if(turn.getStatus()==TurnStatus.MISS){
            cpuField.setImageToGridCell( cell, CSSpicture.MISS);

        }else{
            counterAction();
        }

    }




    private void setListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
                cell.getImageView().onMouseClickedProperty().set( ae -> turn( cell ) );

            }
        }
    }

    private void showPlayersShips() {
        for(Ship ship : cpuField.getFieldData().getShips()){
            System.out.println(ship);
            for(ShipCell shipCell : ship.getShipCellList()){
                int l = shipCell.getLetter();
                int n = shipCell.getNumber();
                FieldCell cell = cpuField.getFieldData().getCells()[l][n];
//                cell.setImage(FieldPicture.DECK);
                cell.setStyle(CSSpicture.DECK);
                GridPane.setConstraints(cell.getImageView(), l, n);
                cpuField.getChildren().add(cell.getImageView());
            }
        }
    }





    public void killShip(Ship ship , FieldGrid fieldGrid) {
        ship.surroundShip(fieldGrid);
        fieldGrid.addKill();

        if(fieldGrid.getCount_kills()==1) gameOver(fieldGrid);

    }

    public void gameOver(FieldGrid fieldGrid){
        Platform.runLater(() -> {
            if(fieldGrid==playerField) controller.showVictory();
            if(fieldGrid==cpuField)    controller.showDefeat();
        });
    }

    private void setHit(FieldGrid fieldGrid , FieldCell fieldCell){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                fieldGrid.setImageToGridCell( fieldCell, CSSpicture.HIT);
            }
        };
        animationTimer.start();


    }


}
