package home.battleShips.model.cpu;

import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LogicHardest implements Logic {

    private final Stack<TurnPattern> patternStack = new Stack<>();
    private final ShipKiller shipKiller = new ShipKiller();

    private Turn lastTurn;

    private TurnPattern pattern;
    private FieldData fieldData ;

    private boolean onlyTorpedoBoats = false;

    @Override
    public void setData(FieldData fieldData) {

        this.fieldData = fieldData;
        TurnPattern.setFieldData(fieldData);

        patternStack.clear();
        patternStack.push(TurnPattern.RANDOM);
        patternStack.push(TurnPattern.THREES);

        patternStack.forEach(TurnPattern::init);
        pattern = patternStack.pop();

    }


    @Override
    public void makeShot() {
        log.info("cpu is shooting....");

        if(shipKiller.isEmpty())  {
            switchPattern();
            Turn turn = pattern.getTurn();

            while(fieldData.isCellInTurns(turn.getCell())){
                switchPattern();
                turn = pattern.getTurn();
            }

            proceedTurn(turn);
        }

        else {
            Turn turn = shipKiller.getNextTurn();

            while(fieldData.isCellInTurns(turn.getCell())){
                turn = shipKiller.getNextTurn();
            }

            proceedTurn(turn);
        }
    }

    private void switchPattern(){
        if(!patternStack.empty()) {
            if (pattern.isEmpty()) {
                pattern = patternStack.pop();
            }

            if (onlyTorpedoBoats && pattern!=TurnPattern.RANDOM) pattern = TurnPattern.RANDOM;
        }
    }

    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    private void proceedTurn(Turn turn){

        lastTurn = turn;
        fieldData.proceedTurn(turn);

        if(turn.isHit()){
            shipKiller.surroundHit(turn.getCell());
            shipKiller.addHit(turn.getCell());
            shipKiller.updateKillingStack();

            if(turn.isKill()){
                shipKiller.clear();
            }
        }

        String info = String.format("cpu shot" +
                " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

        if(!onlyTorpedoBoats && fieldData.areBattleShipsAndCarrierKilled()) {
            onlyTorpedoBoats = true;
        }

    }



}
