package home.battleShips.model.cpu;

import home.battleShips.model.*;
import home.battleShips.utils.TurnSequenceParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hard implements Logic {

    private final static List<Turn> FOURS, TWOS;


    private Turn lastTurn;

    private FieldData fieldData ;

    private List<Turn>  turnPattern;
    private final List<FieldCell>  hitsToKill  = new ArrayList<>();

    private boolean onlyTorpedoBoats = false;


    static {
        TurnSequenceParser turnSequenceParser;
        turnSequenceParser = new TurnSequenceParser("move_sequence/four.txt");
        turnSequenceParser.parse();
        FOURS = turnSequenceParser.getTurns();
        turnSequenceParser = new TurnSequenceParser("move_sequence/two.txt");
        turnSequenceParser.parse();
        TWOS = turnSequenceParser.getTurns();
    }


    @Override
    public void setData(FieldData fieldData) {

        this.fieldData = fieldData;




        turnPattern = new ArrayList<>(FOURS);


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

        if(nextTurns.empty())  {

            Turn turn = getTurnFromPattern();

            proceedTurn(turn);
        }

        else {
            log.info( this.formatStack() );
            Turn turn = nextTurns.pop();
            log.info("cpu is aiming....." + turn);

            while(!fieldData.addTurn(turn)) {
                log.info( formatStack() );
                turn = nextTurns.pop();
                log.info("cpu is aiming....." + turn);
            }

            proceedTurn(turn);
        }
    }



    private Turn getTurnFromPattern( ){

        if(onlyTorpedoBoats) {
//            System.out.println("do random turn");
            return new Turn(fieldData); // random turn
        }else {

//            System.out.println(turnPattern);


            int element = (int) (Math.random() * (turnPattern.size()));
            Turn turn = turnPattern.get(element);



            while (!fieldData.addTurn(turn)) {
                turnPattern.remove(turn);

                element = (int) (Math.random() * (turnPattern.size()));
                turn = turnPattern.get(element);
            }

            turnPattern.remove(turn);
//            if(turnPattern==fours && turnPattern.size()==0) turnPattern = twos;
            return turn;
        }
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

                if(fieldData.onlyTorpedoBoatsLeft()) {
                    onlyTorpedoBoats = true;
                }

            }


        }

        String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

        if(fieldData.isCarrierKilled()) {
//        if(FOURS.size()==0 && turnPattern==FOURS) {
            turnPattern = new ArrayList<>(TWOS);
//            System.out.println(turnPattern.size());
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
