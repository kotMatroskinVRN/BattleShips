package home.battleShips.model.cpu;

import home.battleShips.model.*;



public class LogicNormal implements Logic {

    private Turn lastTurn;
    private FieldData fieldData ;
    private TurnPattern pattern;
    private final NextTurnsStack nextTurns = new NextTurnsStack();


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

        Turn turn = chooseTurn();
        proceedTurn(turn);

    }

    private Turn chooseTurn(){
        Turn turn;
        if(nextTurns.isEmpty())  {
            log.info(  "next turns : empty . Do random hit");

            do {
                turn = pattern.getTurn();
            }
            while (fieldData.isCellInTurns(turn.getCell()));

        }

        else {

            do {
                turn = nextTurns.pop();
            }
            while (fieldData.isCellInTurns(turn.getCell()));

        }
        return turn;
    }


    private void proceedTurn(Turn turn){

        lastTurn = turn;

        fieldData.proceedTurn(turn);
        if(turn.isHit()){

            nextTurns.surroundHit(turn.getCell());

            if(turn.isKill()){
                nextTurns.clear();

            }
        }

        String info = String.format("cpu shot" +
                " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

    }






}
