package home.battleShips.model.cpu;

import home.battleShips.model.*;

import java.util.List;
import java.util.Stack;

public class Hard implements Logic {

    private Turn lastTurn;
    private final Stack<Turn> nextTurns = new Stack<>();
    private FieldData fieldData ;


    @Override
    public void setData(FieldData fieldData) {
        this.fieldData = fieldData;
    }

    @Override
    public Turn getLastTurn(){
        return lastTurn;
    }


    @Override
    public void makeShot() {
        log.info("cpu is shooting....");

        if(nextTurns.empty())  {
//            log.info(  "next turns : empty . Do random hit");
            Turn turn = new Turn(fieldData); // random turn

            proceedTurn(turn);
        }

        else {
            log.info( formatStack() );
            Turn turn = nextTurns.pop();
            log.info("cpu is aiming....." + turn);
            if(fieldData.addTurnIfAbsent(turn)) {
                proceedTurn(turn);
            }else{
                makeShot();
            }
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
                turn.killShip();

            }

            String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
            log.info(info);

        }else {
            String info = String.format("cpu shot" +
                    " %s %s" , turn.getCell() , turn.getStatus());
            log.info(info);

        }

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
            cell.getButton(); // TODO remove monkey patch : check field size!!!

            nextTurns.push( new Turn(cell) );
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            String string = "Wrong L/N :" + letter + number;
            log.warning(string);
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
