package home.battleShips.model.cpu;

import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LogicUltimate implements Logic {

    private final Stack<TurnPattern> patternStack = new Stack<>();

    private Turn lastTurn;

    private TurnPattern pattern;
    private FieldData fieldData ;

    private final List<FieldCell>  hitsToKill  = new ArrayList<>();

    private boolean onlyTorpedoBoats = false;


    @Override
    public void setData(FieldData fieldData) {

        this.fieldData = fieldData;
        TurnPattern.setFieldData(fieldData);

        patternStack.clear();
        patternStack.push(TurnPattern.RANDOM);
        patternStack.push(TurnPattern.ULTIMATE_SECOND);
        patternStack.push(TurnPattern.ULTIMATE_FIRST);

        patternStack.forEach(TurnPattern::init);
        pattern = patternStack.pop();

    }



    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {
        log.info("cpu is shooting....");

        if(nextTurns.empty())  {
            switchPattern();
            Turn turn = pattern.getTurn();

            while(fieldData.isCellInTurns(turn.getCell())){
                switchPattern();
                turn = pattern.getTurn();

            }

            proceedTurn(turn);
        }

        else {
            log.info( this.formatStack() );
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

    private void switchPattern(){
        if(!patternStack.empty()) {
            if (pattern.isEmpty()) {
                pattern = patternStack.pop();
            }

            if (onlyTorpedoBoats && pattern!=TurnPattern.RANDOM) pattern = TurnPattern.RANDOM;
        }
    }




    private void proceedTurn(Turn turn){

        lastTurn = turn;
        fieldData.proceedTurn(turn);

        if(turn.isHit()){
            surroundHit(turn.getCell());
            hitsToKill.add(turn.getCell());
            getKillingSet();

            if(turn.isKill()){
                nextTurns.clear();
                hitsToKill.clear();
            }
        }

        String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

        if(!onlyTorpedoBoats && fieldData.areBattleShipsAndCarrierKilled()) {
            onlyTorpedoBoats = true;
        }


    }

    private void getKillingSet(){

        if(hitsToKill.size()>1){
            Stack<Turn> result = new Stack<>();
            int letter = hitsToKill.get(0).getLetter();
            int number = hitsToKill.get(0).getNumber();
            if(number==hitsToKill.get(1).getNumber()){
                for (Turn turn : nextTurns){
                    if(turn.getCell().getNumber()==number){
                        result.push(turn);
                    }
                }
            }
            if(letter==hitsToKill.get(1).getLetter()){
                for (Turn turn : nextTurns){
                    if(turn.getCell().getLetter()==letter){
                        result.push(turn);
                    }
                }
            }
            nextTurns.clear();
            nextTurns.addAll(result);
        }
    }


}
