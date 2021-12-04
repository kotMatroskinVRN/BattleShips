package home.battleShips.model;

import home.battleShips.Controller;
import home.battleShips.Main;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.cpu.Logic;
import home.battleShips.model.cpu.LogicFactory;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {

    private final int FIELD_SIZE = Main.getFIELD_SIZE() ;

    private final Duration DURATION = Duration.seconds(2);
    private final int CYCLE_COUNT    = 16;
    private List<FadeTransition> ANIMATED_CELLS = new ArrayList<>();

    private final FieldGrid playerField;
    private final FieldGrid cpuField;
    private final  Controller controller;

    private Logic aiLogic ;

    private int turnCount;


    public Game(Controller controller) {
        this.controller = controller;

        turnCount = 1;

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

    public void setDifficulty(LogicFactory value) {
        aiLogic = value.getDifficulty();
        aiLogic.setData(cpuField.getFieldData());
    }

    private void killShip(Ship ship , FieldGrid fieldGrid) {
//        FieldData fieldData = fieldGrid.getFieldData();
//        fieldData.surroundShip(ship);
//        fieldData.addKill();

        fieldGrid.update();

        if(cpuField.getFieldData().getCount_kills()==10) gameOver(cpuField);
        if(playerField.getFieldData().getCount_kills()==10) gameOver(playerField);

    }

    private void turn( FieldCell cell) {

        Turn turn = new Turn(cell);

        if ( playerField.getFieldData().addTurnIfAbsent(turn) )  {

            stopAnimation();


            controller.addPlayerTurn(turn);



            turn.shoot(playerField.getFieldData());
            Button button =playerField.getButton(turn.getCell());
            button.setId(turn.getStatus().getPicture().toString());
            button.applyCss();


            if(turn.isHit()){

                //action then hit

                Ship ship = turn.getShip();
                if(ship.isKilled()){
                    killShip(ship ,playerField);
                    playerField.getFieldData().addKill();
                    playerField.getFieldData().surroundShip(ship);
                    playerField.update();
                }

            }else{
                counterAction();
            }
        }

    }

    private void counterAction() {

        aiLogic.makeShot();
        Turn lastTurn =  aiLogic.getLastTurn();
        System.out.println(turnCount++ + " " + lastTurn);
        //fadeAnimation( lastTurn.getCell().getButton() );
        fadeAnimation( cpuField.getButton(lastTurn.getCell()) );
        controller.addCpuTurn(lastTurn);

        Button button =cpuField.getButton(lastTurn.getCell());
        button.setId(lastTurn.getStatus().getPicture().toString());
        button.applyCss();

        if(lastTurn.isHit()){
            if(lastTurn.isKill()){
                killShip( lastTurn.getShip() , cpuField );
            }
            if(cpuField.getFieldData().getCount_kills()<10) {
                counterAction();
            }

        }

    }

    private void setListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
//                cell.getButton().onMouseClickedProperty().set( ae -> turn( cell ) );
//                cell.getButton().getStyleClass().add("player");
                playerField.getButton(cell).onMouseClickedProperty().set( ae -> turn( cell ) );
                playerField.getButton(cell).getStyleClass().add("player");
            }
        }
    }

    private void removeListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
                playerField.getButton(cell).onMouseClickedProperty().set( null );
                playerField.getButton(cell).getStyleClass().removeAll();

            }
        }
    }

    private void showPlayersShips() {
        for(Ship ship : cpuField.getFieldData().getShips()){
            for(ShipCell shipCell : ship.getShipCellList()){
                int l = shipCell.getLetter();
                int n = shipCell.getNumber();
                FieldCell cell = cpuField.getFieldData().getCells()[l][n];
                cell.setStyle(shipCell.getCssId());
                cpuField.getButton(cell).setId(cell.getCssId().toString());

            }
        }
    }



    private void gameOver(FieldGrid fieldGrid){

        removeListeners();

        Platform.runLater(() -> {
            if(fieldGrid==playerField) controller.showVictory();
            if(fieldGrid==cpuField)    controller.showDefeat();
        });
    }

    private void fadeAnimation(Button button){
        FadeTransition animation = new FadeTransition( DURATION );

        ANIMATED_CELLS.add(animation);

        animation.setNode(button);
        animation.setFromValue(1.0);
        animation.setToValue(0.0);
        animation.setCycleCount(CYCLE_COUNT);
        animation.setAutoReverse(true);
        animation.play();
    }

    private void stopAnimation(){

        for (FadeTransition animation : ANIMATED_CELLS){

            animation.stop();
            animation.setFromValue(1.0);
            animation.setToValue(1.0);
            animation.setCycleCount(1);
            animation.play();
        }
        ANIMATED_CELLS.clear();

    }



}
