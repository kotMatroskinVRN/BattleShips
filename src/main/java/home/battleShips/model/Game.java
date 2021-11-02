package home.battleShips.model;

import home.battleShips.Controller;
import home.battleShips.field.CSSpicture;
import home.battleShips.field.grid.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.cpu.Logic;
import home.battleShips.model.cpu.LogicFactory;
import home.battleShips.utils.StaticUtils;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.util.Duration;

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

    private Logic aiLogic ;

    private int turnCount;

    public Game(Controller controller) {
        this.controller = controller;

        turnCount = 0;

        playerField = new FieldGrid();
        cpuField    = new FieldGrid();

        playerField.init();
           cpuField.init();

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

    public void killShip(Ship ship , FieldGrid fieldGrid) {
        fieldGrid.getFieldData().surroundShip(ship);
        fieldGrid.addKill();

        if(fieldGrid.getCount_kills()==10) gameOver(fieldGrid);

    }

    public void setDifficulty(LogicFactory value) {
        aiLogic = value.getDifficulty();
    }

    private void turn( FieldCell cell) {
        turnCount++;
        System.out.println(turnCount);

        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println(cell.getLetter()+number);


        Turn turn = new Turn(cell);

        for(Ship ship : playerField.getFieldData().getShips()){
            if( ship.hasCell(letter,number )){
                turn.setStatus(TurnStatus.HIT);
                ship.addHit(letter,number);

                playerField.setGridCellStyle( cell, CSSpicture.HIT);

                if(ship.isKilled()){
                    turn.setStatus(TurnStatus.KILL);
                    killShip(ship,playerField);
                }
                break;
            }
        }


        if(turn.getStatus()==TurnStatus.MISS){
            playerField.setGridCellStyle( cell, CSSpicture.MISS);
            counterAction();
        }

    }

    private void counterAction() {
         aiLogic.makeShot(this);
    }

    private void setListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
                cell.getButton().onMouseClickedProperty().set( ae -> turn( cell ) );
                cell.getButton().getStyleClass().add("player");
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
                cell.setStyle(CSSpicture.DECK);
            }
        }
    }



    public void gameOver(FieldGrid fieldGrid){

        removelisteners();

        Platform.runLater(() -> {
            if(fieldGrid==playerField) controller.showVictory();
            if(fieldGrid==cpuField)    controller.showDefeat();
        });
    }

    private void removelisteners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
                cell.getButton().onMouseClickedProperty().set( null );
                cell.getButton().getStyleClass().removeAll();

            }
        }
    }





}
