package home.battleShips;

import home.battleShips.field.CpuTurnAnimation;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.model.cpu.Logic;
import home.battleShips.model.cpu.LogicFactory;
import home.battleShips.utils.BattleShipsLogger;
import javafx.application.Platform;

import java.util.Date;

public class Game {

    private final int FIELD_SIZE = Main.getFIELD_SIZE() ;
    private final CpuTurnAnimation cpuTurnAnimation;

    private final BattleShipsLogger log = BattleShipsLogger.getLogger();


    private final FieldGrid playerField;
    private final FieldGrid cpuField;
    private final Controller controller;

    private Logic aiLogic ;

    private int turnCount;


    public Game(Controller controller) {
        this.controller = controller;

        turnCount = 1;

        playerField = new FieldGrid(controller.getLanguage());
        cpuField    = new FieldGrid(controller.getLanguage());

        playerField.init();
           cpuField.init();

        cpuField.showShips();

        log.printVerbose(new Date().toString());

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



    public void makeTurn(FieldCell cell) {

        Turn turn = new Turn(cell);
        FieldData fieldData = playerField.getFieldData();

        if (!fieldData.isCellInTurns(turn.getCell()) )  {

            cpuTurnAnimation.stopAnimation();
            controller.addPlayerTurnToList(turn);

            fieldData.proceedTurn(turn);
            playerField.applyTurn(turn);


            if(turn.isHit()){

                if(turn.isKill()){

                    controller.getPlayerShipsLeft().killShips(playerField.getFieldData().killedClasses());

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
        log.printInfo(turnCount++ + " " + lastTurn);
        cpuTurnAnimation.fadeAnimation( cpuField.getButton(lastTurn.getCell()) );
        controller.addCpuTurnToList(lastTurn);
        cpuField.applyTurn(lastTurn);


        if(lastTurn.isHit()){
            if(lastTurn.isKill()){
                log.printVerbose("Killing hit" , lastTurn.toString());
                controller.getCpuShipsLeft().killShips(cpuField.getFieldData().killedClasses());
                cpuField.showKilledShip(cpuField.getFieldData().getKilledShip(lastTurn.getCell()));
                checkGameOver(  cpuField );
            }
            if(cpuField.getFieldData().getCount_kills()<10) {

                counterAction();
            }

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
                playerField.getButton(cell).onMouseClickedProperty().set( ae -> makeTurn( cell ) );
                playerField.getButton(cell).getStyleClass().add("player");
            }
        }
    }



}
