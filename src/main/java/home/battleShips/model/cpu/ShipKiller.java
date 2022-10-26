package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ShipKiller {

    private final Logger LOG = Main.getLog();

    private final NextTurnsStack nextTurns =  new NextTurnsStack();
    private final List<FieldCell> hits = new ArrayList<>();

    public void updateKillingStack(){
        if(hits.size()>1){
            makeKillingStack();
        }
    }



    public boolean isEmpty(){
        return nextTurns.isEmpty();
    }

    public Turn getNextTurn(){
        LOG.info( nextTurns.toString() );
        Turn turn = nextTurns.pop();
        LOG.info("cpu is aiming....." + turn);
        return turn;
    }


    public void addHit(FieldCell fieldCell){
        hits.add(fieldCell);
    }

    public void clear(){
        nextTurns.clear();
        hits.clear();
    }

    public void surroundHit(FieldCell hit){
        nextTurns.surroundHit(hit);
    }

    private void makeKillingStack(){

        boolean vertical;
        int pitch;
        if( hits.get(0).getLetter() == hits.get(1).getLetter()){
            vertical = true;
            pitch = hits.get(0).getLetter();
        }else{
            vertical = false;
            pitch = hits.get(0).getNumber();
        }

        nextTurns.filter(vertical,pitch);
    }




}
