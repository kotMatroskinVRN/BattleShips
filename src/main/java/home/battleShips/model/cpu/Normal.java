package home.battleShips.model.cpu;

import home.battleShips.model.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.*;
import home.battleShips.utils.StaticUtils;

import java.util.*;

public class Normal implements Logic {

//    private final List<Turn> turns = new ArrayList<>();
    private Turn lastTurn;
    private final Stack<Turn> nextTurns = new Stack<>();
    private Game game;


    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {


        stopAnimation();

        FieldGrid cpuField = game.getCpuField();
        FieldData fieldData = cpuField.getFieldData();


        if(nextTurns.empty())  {
            System.out.println("next turns : empty" );
            Turn turn = new Turn(fieldData);

            proceedTurn(turn);

        }

        else {
            System.out.println("next turns : " + nextTurns);
            proceedTurn(nextTurns.pop());
        }

        lastTurn = fieldData.getTurns().get( fieldData.getTurns().size()-1 );

    }

    private void proceedTurn(Turn turn){
        FieldGrid cpuField = game.getCpuField();
        FieldData fieldData = cpuField.getFieldData();

        if(turn.actionIfHit(fieldData)){

            nextTurns.clear();
            surroundHits(turn.getShip().getHitCells());

//            finishHim();

            if(turn.getShip().isKilled()){
                nextTurns.clear();
                turn.setStatus(TurnStatus.KILL);
                game.killShip(turn.getShip() , game.getCpuField());
            }
        }

        fade(turn.getCell().getButton());



        System.out.println("cpu:" + turn.getStatus());

    }

    private void isHit(Turn turn) {
        FieldCell cell = turn.getCell();
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println("cpu:" + cell.getLetter()+number);
        for(Ship ship : game.getCpuField().getFieldData().getShips()){
            if( ship.hasCell(letter, number)){
                ship.addHit(letter,number);
                turn.setStatus(TurnStatus.HIT);
                turn.setShip(ship);

                break;
            }
        }
        if(turn.getShip().isKilled()){
            nextTurns.clear();
            turn.setStatus(TurnStatus.KILL);
            game.killShip(turn.getShip() , game.getCpuField());
        }
    }


    private void finishHim() {
//        Turn hitTurn = turns.get(turns.size()-1);
        System.out.println(lastTurn);
        Ship hitShip = lastTurn.getShip();
        System.out.println(hitShip);
        List<ShipCell> shipHits = hitShip.getHitCells();

        if(nextTurns.empty())surroundHits(shipHits);

        if(hitShip.isKilled()) {
            nextTurns.clear();
            return;
        }

//        proceedTurn(nextTurns.pop());
        makeShot();

    }

    private void surroundHits(List<ShipCell> shipHits){
        pushHorisontalTurns(shipHits);
        pushVerticalTurns(shipHits);

    }
    private void pushVerticalTurns(List<ShipCell> shipHits){
        int letter;
        int number;

        letter = shipHits.get(0).getLetter();
        number = shipHits.stream().mapToInt(ShipCell::getNumber).max().getAsInt();
        pushNextTurn(letter , number+1);
        number = shipHits.stream().mapToInt(ShipCell::getNumber).min().getAsInt();
        pushNextTurn(letter , number-1);

    }
    private void pushHorisontalTurns(List<ShipCell> shipHits){
        int number;
        int letter;

        number = shipHits.get(0).getNumber();
        letter = shipHits.stream().mapToInt(ShipCell::getLetter).max().getAsInt();
        pushNextTurn(letter+1, number);
        letter = shipHits.stream().mapToInt(ShipCell::getLetter).min().getAsInt();
        pushNextTurn(letter-1, number);

    }

    private void pushNextTurn(int letter , int number){
        FieldCell cell;
        try {
            cell = game.getCpuField().getFieldData().getCells()[letter][number];
            nextTurns.push( new Turn(cell) );
        }catch (NullPointerException | ArrayIndexOutOfBoundsException ignored){}
    }



}

//TODO why cpu stops do turns after hit