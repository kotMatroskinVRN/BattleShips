package home.battleShips.field.grid;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.grid.FieldCell;
import home.battleShips.model.Ship;
import home.battleShips.model.ShipCell;
import home.battleShips.model.Turn;
import home.battleShips.utils.StaticUtils;
import javafx.scene.control.Button;

import java.util.Stack;

public class FieldData {

    private final int FIELD_SIZE = 11 ;
    private final int FS        = 12 ;

    private final FieldCell[][] cells = new FieldCell[FIELD_SIZE][FIELD_SIZE];
    private Ship[] ships ;
    private final Stack<Turn> cpusTurns = new Stack<>();

    public void init(){

        System.out.println("\ndefault fill array");
        defaultFillArray();
        ships =  randomSetOfShips() ;

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
//                            playField.setGridCellStyle(cell, CSSpicture.MISS);
                        }
                    } catch (NullPointerException  | ArrayIndexOutOfBoundsException ignored){}

                }
            }
        }
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

