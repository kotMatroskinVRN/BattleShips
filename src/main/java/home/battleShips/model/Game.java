package home.battleShips.model;

import home.battleShips.Controller;
import home.battleShips.Main;
import home.battleShips.field.CpuTurnAnimation;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.cpu.Logic;
import home.battleShips.model.cpu.LogicFactory;
import javafx.application.Platform;

import java.util.Date;
import java.util.logging.Logger;

public class Game {

    private final int FIELD_SIZE = Main.getFIELD_SIZE() ;
    private final CpuTurnAnimation cpuTurnAnimation;

    private final Logger log = Main.getLog();


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

        cpuField.showShips();

        System.out.println(new Date());

        setListeners();
        playerField.setPlayerField(true);

        cpuTurnAnimation = new CpuTurnAnimation();


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



    public void turn( FieldCell cell) {

        Turn turn = new Turn(cell);
        FieldData fieldData = playerField.getFieldData();

        if ( fieldData.addTurn(turn) )  {

            cpuTurnAnimation.stopAnimation();
            controller.addPlayerTurnToList(turn);

            turn.shoot(fieldData);
            playerField.applyTurn(turn);


            if(turn.isHit()){

                //action then hit


                if(turn.isKill()){

                    playerField.showKilledShip(fieldData.getKilledShip(turn.getCell()));
                    playerField.update();
                    checkGameOver(playerField);
                }

            }else{
                counterAction();
            }
        }

    }

    private void counterAction() {

        aiLogic.makeShot();
        Turn lastTurn =  aiLogic.getLastTurn();
        log.info(turnCount++ + " " + lastTurn);
        cpuTurnAnimation.fadeAnimation( cpuField.getButton(lastTurn.getCell()) );
        controller.addCpuTurnToList(lastTurn);
        cpuField.applyTurn(lastTurn);

        //pause();

        if(lastTurn.isHit()){
            if(lastTurn.isKill()){
                System.out.println("Killing hit : "+lastTurn);
                cpuField.showKilledShip(cpuField.getFieldData().getKilledShip(lastTurn.getCell()));
                checkGameOver(  cpuField );
            }
            if(cpuField.getFieldData().getCount_kills()<10) {

                counterAction();
            }

        }

    }

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkGameOver(FieldGrid fieldGrid) {

        fieldGrid.update();


        if(cpuField.getFieldData().getCount_kills()==10) gameOver(cpuField);
        if(playerField.getFieldData().getCount_kills()==10) gameOver(playerField);

    }



    private void gameOver(FieldGrid fieldGrid){

        playerField.removeListeners();

        Platform.runLater(() -> {
            if(fieldGrid==playerField) controller.showVictory();
            if(fieldGrid==cpuField)    controller.showDefeat();
        });
    }

    private void setListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
                playerField.getButton(cell).onMouseClickedProperty().set( ae -> turn( cell ) );
                playerField.getButton(cell).getStyleClass().add("player");
            }
        }
    }



}
