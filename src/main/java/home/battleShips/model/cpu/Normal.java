package home.battleShips.model.cpu;

import home.battleShips.model.*;


public class Normal implements Logic {

    private Turn lastTurn;
    private FieldData fieldData ;


    @Override
    public void setData(FieldData fieldData) {
        this.fieldData = fieldData;
    }

//    @Override
//    public FieldData getData(){
//        return fieldData;
//    }

    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {
        log.info("cpu is shooting....");

        if(nextTurns.isEmpty())  {
            log.info(  "next turns : empty . Do random hit");
            Turn turn = new Turn(fieldData); // random turn

            proceedTurn(turn);
        }

        else {
            log.info( formatStack() );

            Turn turn = nextTurns.pop();
            log.info("cpu is aiming....." + turn);


            while(!fieldData.addTurnIfAbsent(turn)) {
                log.info( formatStack() );
                turn = nextTurns.pop();
                log.info("cpu is aiming....." + turn);
            }

            proceedTurn(turn);
        }

    }


    private void proceedTurn(Turn turn){

        lastTurn = turn;

        turn.shoot(fieldData);
        if(turn.isHit()){

            surroundHit(turn.getCell());

            if(turn.isKill()){
                nextTurns.clear();

            }
        }

        String info = String.format("cpu shot" +
                " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

    }




}
