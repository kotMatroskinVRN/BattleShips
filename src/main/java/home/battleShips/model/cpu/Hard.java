package home.battleShips.model.cpu;

import home.battleShips.model.*;
import home.battleShips.utils.TurnSequenceParser;

import java.util.ArrayList;
import java.util.List;

public class Hard implements Logic {

    private final static List<Turn> FOURS, TWOS;


    private Turn lastTurn;

    private FieldData fieldData ;

    private List<Turn>  turnPattern;
    private List<Ship> killedShips ;

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


        killedShips = new ArrayList<>();

        turnPattern = new ArrayList<>(FOURS);
//        System.out.println(turnPattern.size());


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

        if(nextTurns.empty())  {

            Turn turn = getTurnFromPattern();

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

    private boolean isCarrierKilled(){
        for( Ship ship : killedShips ){
            if(ship.getSize()==4) return true;
        }
        return false;
    }

    private boolean onlyTorpedoBoatsLeft(){
        int battleShips = 0;
        int destroyers  = 0;

        for( Ship ship : killedShips){
            if(ship.getSize()==3) battleShips++;
            if(ship.getSize()==2) destroyers++;
        }

        return battleShips == 2 && destroyers == 3 && isCarrierKilled();
    }

    private Turn getTurnFromPattern( ){

        if(onlyTorpedoBoats) {
//            System.out.println("do random turn");
            return new Turn(fieldData); // random turn
        }else {

//            System.out.println(turnPattern);


            int element = (int) (Math.random() * (turnPattern.size()));
            Turn turn = turnPattern.get(element);



            while (!fieldData.addTurnIfAbsent(turn)) {
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

            nextTurns.clear();
            surroundHits(turn.getShip().getHitCells());

            if(turn.getShip().isKilled()){
                nextTurns.clear();
                fieldData.addKill(turn.getShip());

                killedShips.add(turn.getShip());


                if(onlyTorpedoBoatsLeft()) {
                    onlyTorpedoBoats = true;
                }

            }


        }

        String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
        log.info(info);

        if(isCarrierKilled()) {
//        if(FOURS.size()==0 && turnPattern==FOURS) {
            turnPattern = new ArrayList<>(TWOS);
//            System.out.println(turnPattern.size());
        }


    }



}
