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
//    private FieldGrid cpuField ;
    private FieldData fieldData ;


    @Override
    public void setGame(Game game) {
        this.game = game;
//        cpuField = game.getCpuField();
        fieldData = game.getCpuField().getFieldData();
    }

    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {


        stopAnimation();


        if(nextTurns.empty())  {
//            log.info(  "next turns : empty . Do random hit");
            Turn turn = new Turn(fieldData); // random turn

            proceedTurn(turn);
        }

        else {
            log.info( formatStack() );
            Turn turn = nextTurns.pop();
            if(fieldData.addTurnIfAbsent(turn)) {
                proceedTurn(turn);
            }else{
                makeShot();
            }
        }



    }

    private void proceedTurn(Turn turn){

        lastTurn = turn;


        turn.shoot(fieldData);
        if(turn.isHit()){

            nextTurns.clear();
            surroundHits(turn.getShip().getHitCells());

//            finishHim();

            if(turn.getShip().isKilled()){
                nextTurns.clear();
                turn.setStatus(TurnStatus.KILL);
                game.killShip(turn.getShip() , game.getCpuField());
            }

            String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
            log.info(info);

        }else {
            String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
            System.out.println(info);

            fade(turn.getCell().getButton());
        }



    }

    private void isHit(Turn turn) {
        FieldCell cell = turn.getCell();
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        log.info("cpu:" + cell.getLetter()+number);
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
        //makeShot();

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

            // invoke exception when letter or number is out of field
            cell.getButton(); // TODO remove monkey patch : check field size!!!

            nextTurns.push( new Turn(cell) );
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            String string = "Wrong L/N :" + letter + number;
            log.warning(string);
        }
    }

    private String formatStack(){
        StringBuilder result = new StringBuilder();
        result.append("NextTurn size : ").append( nextTurns.size() ).append("\n");
        result.append("\t\t");
        result.append("next turns : " );

        for(Turn turn : nextTurns){
            try {
                result.append( turn.toString() );
            }catch (NullPointerException npe){
                log.severe("turn has no cell" + turn.hashCode());
                npe.printStackTrace();

            }
        }
        return result.toString();
    }

}

//TODO why cpu stops do turns after hit