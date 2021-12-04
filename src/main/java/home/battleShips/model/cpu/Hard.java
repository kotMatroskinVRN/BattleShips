package home.battleShips.model.cpu;

import home.battleShips.model.*;
import home.battleShips.utils.TurnSequenceParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hard implements Logic {

    private Turn lastTurn;
    private Stack<Turn> nextTurns ;
    private FieldData fieldData ;
    private List<Turn> fours  , twos , turnPattern;
    private List<Ship> killedShips ;

    private boolean onlyTorpedoBoats = false;


    @Override
    public void setData(FieldData fieldData) {

        this.fieldData = fieldData;

        nextTurns   = new Stack<>();
        killedShips = new ArrayList<>();

        TurnSequenceParser turnSequenceParser;
        turnSequenceParser = new TurnSequenceParser("move_sequence/four.txt");
        turnSequenceParser.parse();
        fours = turnSequenceParser.getTurns();
        turnSequenceParser = new TurnSequenceParser("move_sequence/two.txt");
        turnSequenceParser.parse();
        twos = turnSequenceParser.getTurns();

        turnPattern = fours;
//        System.out.println(turnSequenceParser.getTurns().size());
//        System.out.println(turnSequenceParser.getTurns().get(23));


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
//                System.out.println(turn + " exist in");
//                System.out.println(fieldData.getTurns());
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
                fieldData.addKill();
                fieldData.surroundShip(turn.getShip());

                killedShips.add(turn.getShip());

                if(isCarrierKilled()) {
                    turnPattern = twos;
//                    System.out.println("Carrier is killed");
                }
                if(onlyTorpedoBoatsLeft()) {
                    onlyTorpedoBoats = true;
                }

            }


        }
            String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
            log.info(info);



    }


    private void surroundHits(List<ShipCell> shipHits){
        pushHorisontalTurns(shipHits);
        pushVerticalTurns(shipHits);
    }
    private void pushVerticalTurns(List<ShipCell> shipHits){
        int letter;
        int number;

        letter = shipHits.get(0).getLetter();
        number = shipHits.stream().mapToInt(ShipCell::getNumber).max().getAsInt();
        pushNextTurn(letter , number+1);
        number = shipHits.stream().mapToInt(ShipCell::getNumber).min().getAsInt();
        pushNextTurn(letter , number-1);

    }
    private void pushHorisontalTurns(List<ShipCell> shipHits){
        int number;
        int letter;

        number = shipHits.get(0).getNumber();
        letter = shipHits.stream().mapToInt(ShipCell::getLetter).max().getAsInt();
        pushNextTurn(letter+1, number);
        letter = shipHits.stream().mapToInt(ShipCell::getLetter).min().getAsInt();
        pushNextTurn(letter-1, number);

    }

    private void pushNextTurn(int letter , int number){
        FieldCell cell;
        try {
            cell = fieldData.getCells()[letter][number];

            // invoke exception when letter or number is out of field
            cell.toString(); // TODO remove monkey patch : check field size!!!

            nextTurns.push( new Turn(cell) );
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            String string = "Wrong L/N :" + letter + number;
            log.info(string);
        }
    }

    private String formatStack(){
        StringBuilder result = new StringBuilder();
        result.append("NextTurn size : ").append( nextTurns.size() ).append("\n");
        result.append("\t\t");
        result.append("next turns : " );

        for(Turn turn : nextTurns){
            try {
                result.append( turn.toString() );
            }catch (NullPointerException npe){
                log.severe("turn has no cell" + turn.hashCode());
                npe.printStackTrace();

            }
        }
        return result.toString();
    }

}
