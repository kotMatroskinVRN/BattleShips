package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;

import java.util.Stack;
import java.util.logging.Logger;

public interface Logic {

    Logger log = Main.getLog();
    Stack<Turn> nextTurns =  new Stack<>();

    void setData(FieldData fieldData);
//    FieldData getData();
    void makeShot();
    Turn getLastTurn();

    default void surroundHit(FieldCell hit){
        pushHorisontalTurns(hit);
        pushVerticalTurns(hit);
    }
    default void pushVerticalTurns(FieldCell hit){
        pushNextTurn(hit.getLetter() , hit.getNumber()+1);
        pushNextTurn(hit.getLetter() , hit.getNumber()-1);

    }
    default void pushHorisontalTurns(FieldCell hit){
        pushNextTurn(hit.getLetter()+1 , hit.getNumber());
        pushNextTurn(hit.getLetter()-1 , hit.getNumber());

    }

    default void pushNextTurn(int letter , int number){
        FieldCell cell;
        try {
//            cell = getData().getCells()[letter][number];
            cell = new FieldCell(letter,number);

            // invoke exception when letter or number is out of field
            cell.toString();

            nextTurns.push( new Turn(cell) );
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            String string = "Wrong L/N :" + letter + number;
            log.info(string);
        }
    }

    default String formatStack(){
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
