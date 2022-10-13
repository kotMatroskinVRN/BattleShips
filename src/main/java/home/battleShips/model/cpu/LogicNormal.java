package home.battleShips.model.cpu;

import home.battleShips.model.*;


public class LogicNormal implements Logic {

    private Turn lastTurn;
    private FieldData fieldData ;
    private TurnPattern pattern;


    @Override
    public void setData(FieldData fieldData) {
        this.fieldData = fieldData;
        TurnPattern.setFieldData(fieldData);
        pattern = TurnPattern.RANDOM;
        pattern.init();
    }


    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {
        log.info("cpu is shooting....");

        if(nextTurns.isEmpty())  {
            log.info(  "next turns : empty . Do random hit");

            Turn turn = pattern.getTurn(); // random turn
            while(fieldData.isCellInTurns(turn.getCell())){
                turn = pattern.getTurn(); // random turn

            }

            proceedTurn(turn);
        }

        else {
            log.info( formatStack() );

            Turn turn = nextTurns.pop();
            log.info("cpu is aiming....." + turn);


            while(fieldData.isCellInTurns(turn.getCell())) {
                log.info( formatStack() );
                turn = nextTurns.pop();
                log.info("cpu is aiming....." + turn);
            }

            proceedTurn(turn);
        }

    }


    private void proceedTurn(Turn turn){

        lastTurn = turn;


        fieldData.proceedTurn(turn);
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
