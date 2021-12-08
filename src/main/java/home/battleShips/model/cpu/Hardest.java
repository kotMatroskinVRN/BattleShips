package home.battleShips.model.cpu;

import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.utils.TurnSequenceParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hardest implements Logic {

    private final static List<Turn> THREES;


    private Turn lastTurn;

    private FieldData fieldData ;
    private List<Turn>  turnPattern;
    private final List<FieldCell>  hitsToKill  = new ArrayList<>();

    private boolean onlyTorpedoBoats = false;


    static {
        TurnSequenceParser turnSequenceParser;
        turnSequenceParser = new TurnSequenceParser("move_sequence/threes.txt");
        turnSequenceParser.parse();
        THREES = turnSequenceParser.getTurns();
//        turnSequenceParser = new TurnSequenceParser("move_sequence/four_three_center.txt");
//        turnSequenceParser.parse();
//        TWOS = turnSequenceParser.getTurns();


    }


    @Override
    public void setData(FieldData fieldData) {

        this.fieldData = fieldData;
        turnPattern = new ArrayList<>(THREES);
    }


    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {
        log.info("cpu is shooting....");

        if(nextTurns.empty())  {

            Turn turn = getTurnFromPattern();

            proceedTurn(turn);
        }

        else {
            log.info( this.formatStack() );
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



    private Turn getTurnFromPattern( ){

        if(onlyTorpedoBoats) {
            return new Turn(fieldData); // random turn
        }
        Turn turn = getRandomTurnFromPattern();

        boolean factor = fieldData.addTurnIfAbsent(turn);
        while (!factor) {
            turn = getRandomTurnFromPattern();
            factor = fieldData.addTurnIfAbsent(turn);

        }

        return turn;

    }

    private Turn getRandomTurnFromPattern(){
        whenFoursEmpty();
        if(onlyTorpedoBoats) {
            return new Turn(fieldData); // random turn
        }
        int element = (int) (Math.random() * (turnPattern.size()));
        Turn turn = turnPattern.get(element);
        turnPattern.remove(turn);

        return turn;
    }

    private void whenFoursEmpty(){
//        if(turnPattern.size()==0) turnPattern = new ArrayList<>(TWOS);
        if(turnPattern.size()==0) onlyTorpedoBoats = true;
    }

    private void proceedTurn(Turn turn){

        lastTurn = turn;

        turn.shoot(fieldData);
        if(turn.isHit()){

            surroundHit(turn.getCell());
            hitsToKill.add(turn.getCell());
            getKillingSet();

            if(turn.isKill()){
                nextTurns.clear();
                hitsToKill.clear();

//                if(fieldData.areBattleShipsKilled()) {
//                    onlyTorpedoBoats = true;
//                }

            }


        }

        String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);




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
