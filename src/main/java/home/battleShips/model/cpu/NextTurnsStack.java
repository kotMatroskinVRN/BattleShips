package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.Turn;

import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NextTurnsStack {

    private final Logger LOG = Main.getLog();
    private final Stack<Turn> nextTurns =  new Stack<>();

    public Turn pop() {
        LOG.info( toString() );
        Turn turn = nextTurns.pop();
        LOG.info("cpu is aiming....." + turn);
        return turn;
    }

    public void surroundHit(FieldCell hit){
        pushHorisontalTurns(hit);
        pushVerticalTurns(hit);
    }

    public boolean isEmpty(){
        return nextTurns.isEmpty();
    }
    public void clear(){
        nextTurns.clear();
    }
    public void addAll(Stack<Turn> turns){
        nextTurns.addAll(turns);
    }

    public void filter(boolean vertical , int pitch){
        List<Turn> result ;

        if(vertical) {
            result = nextTurns.stream()
                    .filter(turn -> turn.getCell().getLetter()==pitch)
                    .collect(Collectors.toList());
        }else{
            result = nextTurns.stream()
                    .filter(turn -> turn.getCell().getNumber()==pitch)
                    .collect(Collectors.toList());
        }

        nextTurns.clear();
        nextTurns.addAll(result);

    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("NextTurn size : ").append( nextTurns.size() ).append("\n");
        result.append("\t\t");
        result.append("next turns : " );

        for(Turn turn : nextTurns){
            try {
                result.append( turn.toString() );
            }catch (NullPointerException npe){
                LOG.severe("turn has no cell" + turn.hashCode());
                npe.printStackTrace();

            }
        }
        return result.toString();
    }




    private void pushVerticalTurns(FieldCell hit){
        pushNextTurn(hit.getLetter() , hit.getNumber()+1);
        pushNextTurn(hit.getLetter() , hit.getNumber()-1);

    }
    private void pushHorisontalTurns(FieldCell hit){
        pushNextTurn(hit.getLetter()+1 , hit.getNumber());
        pushNextTurn(hit.getLetter()-1 , hit.getNumber());

    }

    private void pushNextTurn(int letter , int number){
        FieldCell cell;
        try {
//            cell = getData().getCells()[letter][number];
            cell = new FieldCell(letter,number);

            // invoke exception when letter or number is out of field
            cell.toString();

            nextTurns.push( new Turn(cell) );
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            String string = "Wrong L/N :" + letter + number;
            LOG.info(string);
        }
    }



}
