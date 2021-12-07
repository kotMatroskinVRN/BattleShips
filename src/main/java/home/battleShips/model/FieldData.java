package home.battleShips.model;

import home.battleShips.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FieldData {

    private final int FIELD_SIZE = Main.getFIELD_SIZE();

    private final FieldCell[][] cells = new FieldCell[FIELD_SIZE][FIELD_SIZE];
    private Ship[] ships ;
    private final Set<Turn> turns = new HashSet<>();
    private final List<Ship> killedShips = new ArrayList<>();



    public void init(){

        turns.clear();

        defaultFillArray();
        ships =  randomSetOfShips() ;


    }



    public boolean addTurnIfAbsent(Turn turn){
        FieldCell cell = turn.getCell();


        if(isCellInTurns(cell)) {
            return false;
        }

        turns.add(turn);
        return true;
    }

    public boolean isHit(FieldCell cell){

        for(Ship ship : ships){
            if( ship.hasCell(cell)){
                ship.addHit(cell);
                return true;
            }
        }
        return false;

    }

    public boolean isShipKilled(FieldCell cell){
        for(Ship ship : ships){
            if( ship.hasCell(cell) && ship.isKilled() ){
                if(!killedShips.contains(ship)) {
                    killedShips.add(ship);
                    surroundShip(ship);
                }
                return true;
            }
        }
        return false;
    }

    public Set<Turn> getTurns() {
        return turns;
    }


    public FieldCell[][] getCells() {
        return cells;
    }

    public Ship[] getShips() {
        return ships;
    }

    public boolean isCarrierKilled(){
        for( Ship ship : killedShips ){
            if(ship.getSize()==4) return true;
        }
        return false;
    }

    public boolean areBattleShipsKilled(){
        int battleShips = 0;


        for( Ship ship : killedShips){
            if(ship.getSize()==3) battleShips++;
        }

        return battleShips == 2 && isCarrierKilled();
    }

    public boolean onlyTorpedoBoatsLeft(){
        int battleShips = 0;
        int destroyers  = 0;

        for( Ship ship : killedShips){
            if(ship.getSize()==3) battleShips++;
            if(ship.getSize()==2) destroyers++;
        }

        return battleShips == 2 && destroyers == 3 && isCarrierKilled();
    }

    public Ship getKilledShip(FieldCell cell){
        for(Ship ship : killedShips){
            if(ship.hasCell(cell)){
                return ship;
            }
        }
        return null;
    }

    public void surroundShip(Ship ship ) {
        for(FieldCell shipCell : ship.getShipCellList()){
            for(int dl=-1;dl<=1;dl++) {
                for (int dn = -1; dn <= 1; dn++) {
                    int letter = shipCell.getLetter()+dl;
                    int number = shipCell.getNumber()+dn;
                    try {

                        if(!ship.hasCell(letter,number)){
                            FieldCell cell = cells[letter][number];
                            cell.setStyle(CssId.MISS);
                            Turn turn = new Turn(cell);
                            addTurnIfAbsent(turn);
                        }
                    } catch (NullPointerException  | ArrayIndexOutOfBoundsException ignored){}

                }
            }
            cells[shipCell.getLetter()][shipCell.getNumber()].setStyle(CssId.HIT);
        }
    }

    public int getCount_kills() {
        return killedShips.size();
    }


    private boolean isCellInTurns(FieldCell cell){
        int cellLetter = cell.getLetter();
        int cellNumber = cell.getNumber();
        for(Turn t: turns){
            int letter = t.getCell().getLetter();
            int number = t.getCell().getNumber();
            if(letter==cellLetter && number==cellNumber) return true;
        }
        return false;
    }



    private void defaultFillArray() {
        for(char letter=1; letter<FIELD_SIZE  ;letter++ ){
            for(int number=1; number<FIELD_SIZE; number++){
                FieldCell cell = new FieldCell(letter , number);
                cells[letter][number] = cell;

            }
        }
    }

    private Ship[] randomSetOfShips() {


        int size , n , l ;
        char  dc;
        char[] d = { 'h' , 'v' };
        Ship[] rShips = new Ship[FIELD_SIZE-1] ;

        for(int i = 0 ; i<FIELD_SIZE-1 ; i++ ){
            size = (int)( 12 - 1.5*i )/4 + 1 ;
            l  = (int)( Math.random()*(FIELD_SIZE-size) ) +1  ;
            n  = (int)( Math.random()*(FIELD_SIZE-size) ) +1  ;
            dc = d[(int)( Math.random()*2)] ;

            rShips[i] = new Ship( size , l  , n , dc );


            while( i>0 && !( checkShipsArray( rShips , i ) ) ){
                l  = (int)( Math.random()*(FIELD_SIZE-size) ) +1  ;
                n  = (int)( Math.random()*(FIELD_SIZE-size) ) +1  ;
                dc = d[(int)( Math.random()*2)] ;
                rShips[i] = new Ship( size , l  , n , dc );
            }
        }



        return rShips ;
    }

    private boolean checkShipsArray(Ship[] ships , int size){

        for( int i=0;i<=size;i++ ){
            for( int j=0;j<=size;j++ ){
                if( i!=j && !( ships[i].check2Ships(ships[j]) ) ){
                    return false;
                }
            }
        }

        return true ;
    }
}

