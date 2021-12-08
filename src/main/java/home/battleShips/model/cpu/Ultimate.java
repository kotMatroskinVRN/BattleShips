package home.battleShips.model.cpu;

import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.utils.TurnSequenceParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Ultimate implements Logic {

//    private final static List<Turn> FOURS, TWOS;
    private static int patternSwitchedTimes ;

    private Turn lastTurn;

    private UltimatePattern pattern;
    private FieldData fieldData ;
//    private List<Turn>  turnPattern;
    private final List<FieldCell>  hitsToKill  = new ArrayList<>();

    private boolean onlyTorpedoBoats = false;


//    static {
//        TurnSequenceParser turnSequenceParser;
//        turnSequenceParser = new TurnSequenceParser("move_sequence/four_three.txt");
//        turnSequenceParser.parse();
//        FOURS = turnSequenceParser.getTurns();
//        turnSequenceParser = new TurnSequenceParser("move_sequence/four_three_center.txt");
//        turnSequenceParser.parse();
//        TWOS = turnSequenceParser.getTurns();
//
//
//    }


    @Override
    public void setData(FieldData fieldData) {

        this.fieldData = fieldData;
//        turnPattern = new ArrayList<>(FOURS);
        patternSwitchedTimes = 0;
        UltimatePattern.RANDOM.setFieldData(fieldData);
        pattern = UltimatePattern.FIRST;

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

            while(pattern!=UltimatePattern.RANDOM && !fieldData.addTurnIfAbsent(turn)) {
                switchPattern();
                turn = pattern.getTurn();
            }
            if(pattern==UltimatePattern.RANDOM){
                turn = pattern.getTurn();
            }

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

    private void switchPattern(){
        if(UltimatePattern.FIRST.isEmpty())  pattern = UltimatePattern.SECOND;
        if(UltimatePattern.SECOND.isEmpty()) pattern = UltimatePattern.RANDOM;
    }


//    private Turn getTurnFromPattern( ){
//         getRandomTurnFromPattern();
//
//
//        return turn;
//
//    }

//    private Turn getRandomTurnFromPattern(){
//        whenFoursEmpty();
//        if(turnPattern.size()==0) {
//            return new Turn(fieldData); // random turn
//        }
//        int element = (int) (Math.random() * (turnPattern.size()));
//        Turn turn = turnPattern.get(element);
//        turnPattern.remove(turn);
//
//        return turn;
//    }
//
//    private void whenFoursEmpty(){
//        if(patternSwitchedTimes==0 && turnPattern.size()==0) {
//            turnPattern = new ArrayList<>(TWOS);
//            patternSwitchedTimes++;
//            log.warning("switched");
//        }
//    }

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

                if(fieldData.areBattleShipsKilled()) {
                    onlyTorpedoBoats = true;
                }

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
