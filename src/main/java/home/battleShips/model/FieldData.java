package home.battleShips.model;

import home.battleShips.field.CSSpicture;
import home.battleShips.utils.StaticUtils;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.List;


public class FieldData {

    private final int FIELD_SIZE = 11 ;
    private final int FS        = 12 ;

    private final FieldCell[][] cells = new FieldCell[FIELD_SIZE][FIELD_SIZE];
    private Ship[] ships ;
    private final ObservableList<Turn> turns = FXCollections.observableArrayList() ;

    public void init(){

        //System.out.println("\ndefault fill array");
        defaultFillArray();
        ships =  randomSetOfShips() ;

        turns.addListener((InvalidationListener) change -> {

                for(Turn turn : turns){
                    turn.getCell().setStyle( turn.getStatus().getPicture() );
                }
        });

    }

    public void killShip(Turn turn){
        turn.setStatus(TurnStatus.KILL);

    }


    public boolean hasTurn(Turn turn){
        FieldCell cell = turn.getCell();
        return isCellInTurns(cell);
    }
    public boolean hasTurn(FieldCell cell){
        return isCellInTurns(cell);
    }

    public boolean addTurnIfAbsent(Turn turn){
        FieldCell cell = turn.getCell();

        if(isCellInTurns(cell)) return false;

        turns.add(turn);
        //System.out.println(turns);
        return true;
    }
    public boolean addTurnIfAbsent(FieldCell cell){
        if(isCellInTurns(cell)) return false;
        turns.add( new Turn(cell) );
        System.out.println(turns);
        return true;
    }

    public ObservableList<Turn> getTurns(){
        return turns;
    }

    public Button getButton(String letter , int number){
        return chooseCell(letter,number).getButton();
    }

    public FieldCell[][] getCells() {
        return cells;
    }

    public Ship[] getShips() {
        return ships;
    }

    public void surroundShip(Ship ship ) {
        for(ShipCell shipCell : ship.getShipCellList()){
            for(int dl=-1;dl<=1;dl++) {
                for (int dn = -1; dn <= 1; dn++) {
                    int letter = shipCell.getLetter()+dl;
                    int number = shipCell.getNumber()+dn;
                    try {

                        if(!ship.hasCell(letter,number)){
                            FieldCell cell = cells[letter][number];
                            cell.setStyle(CSSpicture.MISS);
                            Turn turn = new Turn(cell);
                            addTurnIfAbsent(turn);
//                            playField.setGridCellStyle(cell, CSSpicture.MISS);
                        }
                    } catch (NullPointerException  | ArrayIndexOutOfBoundsException ignored){}

                }
            }
        }
    }

    private boolean isCellInTurns(FieldCell cell){
        int cellLetter = StaticUtils.getNumberFromChar(cell.getLetter());
        int cellNumber = cell.getNumber();
        for(Turn t: turns){
            int letter = StaticUtils.getNumberFromChar(t.getCell().getLetter());
            int number = t.getCell().getNumber();
            if(letter==cellLetter && number==cellNumber) return true;
        }
        return false;
    }

    private FieldCell chooseCell(String letter , int number){
        return cells[StaticUtils.getNumberFromChar(letter)][number];
    }


    private void defaultFillArray() {
        for(char c='А'; c <= 'К' ;c++ ){
            for(int i=1; i<FIELD_SIZE; i++){

                if(c=='Й') continue;
                int arrayNumber= StaticUtils.getNumberFromChar(c);
                //System.out.println("defaultFill : " + arrayNumber);
                String letter = String.valueOf(c);
                FieldCell cell = new FieldCell(letter , i);
                cells[arrayNumber][i] = cell;

            }
        }
    }

    private Ship[] randomSetOfShips() {


        int size , n , l ;
        char  dc;
        char[] d = { 'h' , 'v' };
        Ship[] rShips = new Ship[FS-2] ;

        for(int i = 0 ; i<FS-2 ; i++ ){
            size = (int)( 12 - 1.5*i )/4 + 1 ;
            l  = (int)( Math.random()*(FS-1-size) ) +1  ;
            n  = (int)( Math.random()*(FS-1-size) ) +1  ; // fs-1????
            dc = d[(int)( Math.random()*2)] ;

            rShips[i] = new Ship( size , l  , n , dc );


            while( i>0 && !( checkShipsArray( rShips , i ) ) ){
                l  = (int)( Math.random()*(FS-1-size) ) +1  ;
                n  = (int)( Math.random()*(FS-1-size) ) +1  ;
                dc = d[(int)( Math.random()*2)] ;
                rShips[i] = new Ship( size , l  , n , dc );
            }
            //System.out.printf("%d is O.K. \n" , size );
        }// for ships array



        return rShips ;
    }//randomSetOfShips

    private boolean checkShipsArray(Ship[] ships , int size){

        for( int i=0;i<=size;i++ ){
            for( int j=0;j<=size;j++ ){
                if( i!=j && !( ships[i].check2Ships(ships[j]) ) ){
                    return false;
                }
            }
        }

        return true ;
    }//checkShipsArray
}

