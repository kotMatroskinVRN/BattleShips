package home.battleShips.model.cpu;

import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.utils.TurnSequenceParser;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Stack;

public class Hardest implements Logic {

    private final Stack<TurnPattern> patternStack = new Stack<>();

    private Turn lastTurn;

    private TurnPattern pattern;
    private FieldData fieldData ;

    private final List<FieldCell>  hitsToKill  = new ArrayList<>();

    private boolean onlyTorpedoBoats = false;



    @Override
    public void setData(FieldData fieldData) {

        this.fieldData = fieldData;

        patternStack.push(TurnPattern.RANDOM);
        patternStack.push(TurnPattern.THREES);

        patternStack.forEach(TurnPattern::init);
        System.out.println(patternStack);
        pattern = patternStack.pop();
        System.out.println(patternStack);




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

            if(turn==null){
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
                System.out.println("Swithed from : " + pattern.toString());
                pattern = patternStack.pop();
                System.out.println("Swithed to   : " + pattern.toString());
            }

//            if (onlyTorpedoBoats) pattern = TurnPattern.RANDOM;
        }
    }



    private void proceedTurn(Turn turn){

        lastTurn = turn;

        turn.shoot(fieldData);
        fieldData.addTurn(turn);
        if(turn.isHit()){

            surroundHit(turn.getCell());
            hitsToKill.add(turn.getCell());
            getKillingSet();

            if(turn.isKill()){
                nextTurns.clear();
                hitsToKill.clear();

                if(fieldData.areBattleShipsKilled()) {
                    onlyTorpedoBoats = true;
                }

            }


        }

        String info = String.format("cpu shot" +
                " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

        if(fieldData.isCarrierKilled()) {
            pattern = patternStack.pop();
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
