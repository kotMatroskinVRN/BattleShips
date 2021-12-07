package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;

import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public interface Logic {

    Logger log = Main.getLog();
    Stack<Turn> nextTurns =  new Stack<>();

    void setData(FieldData fieldData);
    FieldData getData();
    void makeShot();
    Turn getLastTurn();

    default void surroundHits(List<FieldCell> shipHits){
        pushHorisontalTurns(shipHits);
        pushVerticalTurns(shipHits);
    }
    default void pushVerticalTurns(List<FieldCell> shipHits){
        int letter;
        int number;

        letter = shipHits.get(0).getLetter();
        number = shipHits.stream().mapToInt(FieldCell::getNumber).max().getAsInt();
        pushNextTurn(letter , number+1);
        number = shipHits.stream().mapToInt(FieldCell::getNumber).min().getAsInt();
        pushNextTurn(letter , number-1);

    }
    default void pushHorisontalTurns(List<FieldCell> shipHits){
        int number;
        int letter;

        number = shipHits.get(0).getNumber();
        letter = shipHits.stream().mapToInt(FieldCell::getLetter).max().getAsInt();
        pushNextTurn(letter+1, number);
        letter = shipHits.stream().mapToInt(FieldCell::getLetter).min().getAsInt();
        pushNextTurn(letter-1, number);

    }

    default void pushNextTurn(int letter , int number){
        FieldCell cell;
        try {
            cell = getData().getCells()[letter][number];

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
