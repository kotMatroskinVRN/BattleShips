package home.battleShips.model.cpu;

import home.battleShips.model.*;

import java.util.*;

public class Normal implements Logic {

    private Turn lastTurn;
    private final List<Turn> nextTurns = new ArrayList<>();
    private FieldData fieldData ;


    @Override
    public void setData(FieldData fieldData) {
        this.fieldData = fieldData;
    }

    @Override
    public FieldData getData(){
        return fieldData;
    }

    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {
        log.info("cpu is shooting....");

        if(nextTurns.isEmpty())  {
//            log.info(  "next turns : empty . Do random hit");
            Turn turn = new Turn(fieldData); // random turn

            proceedTurn(turn);
        }

        else {
            log.info( formatStack() );

            Turn turn = getTurn();
            log.info("cpu is aiming....." + turn);
            if(fieldData.addTurnIfAbsent(turn)) {
                proceedTurn(turn);
            }else{
                makeShot();
            }
        }

    }

    private Turn getTurn(){
//        Collections.shuffle(nextTurns);
        Turn turn = nextTurns.get(nextTurns.size()-1);
        nextTurns.remove(turn);
        return turn ;
    }

    private void proceedTurn(Turn turn){

        lastTurn = turn;

        turn.shoot(fieldData);
        if(turn.isHit()){

            nextTurns.clear();
            surroundHits(turn.getShip().getHitCells());

            if(turn.getShip().isKilled()){
                nextTurns.clear();
                fieldData.addKill(turn.getShip());
            }
        }

        String info = String.format("cpu shot" +
                " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

    }




}
