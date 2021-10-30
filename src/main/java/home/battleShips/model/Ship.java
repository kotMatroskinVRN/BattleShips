package home.battleShips.model;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.FieldCell;
import home.battleShips.field.FieldPicture;
import home.battleShips.field.grid.FieldGrid;

import java.util.ArrayList;
import java.util.List;

public class Ship{


    static final int   FS = Game.FS ;


    private final int size ;

    private final List<ShipCell> shipCellList = new ArrayList<>();
    private final List<ShipCell> hitCells     = new ArrayList<>();

    private boolean 		alive  ;

    public Ship( int s , int li , int ni , char d ){

        if(s<1){ System.out.printf("Ship %2d %c%d position is out of range of size \n" , s , li , ni );	 }

        // split to 2 sections : start position and end position
        if (d == 'v') {
            for (int i = 0; i < s; i++) {
                shipCellList.add( new ShipCell(li,ni+i) );
            }
        } else {
            for (int i = 0; i < s; i++) {
                shipCellList.add( new ShipCell(li+i,ni) );
            }
        }

        size = s;
        alive = true ;
    }

    public static Ship[] randomSetOfShips() {


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





    public List<ShipCell> getShipCellList() {
        return shipCellList;
    }

    public List<ShipCell> getHitCells() {
        return hitCells;
    }

    public boolean hasCell(int l , int n){
        for(ShipCell cell : shipCellList){
            if(cell.getLetter() == l && cell.getNumber() == n) return true;
        }
        return false;
    }

    public boolean hasHit(int l , int n){
        if(!hasCell(l,n)) return false;

        for(ShipCell cell : hitCells){
            if(cell.getLetter() == l && cell.getNumber() == n) return true;
        }

        return false;
    }

    public void addHit(int l , int n){
        if(hasCell(l,n)){
            hitCells.add( new ShipCell(l , n) );
        }
        else throw new IndexOutOfBoundsException();
    }

    public boolean isKilled(){
        return hitCells.size() >= size  ;
    }

    // check positions of THIS ship and given one
    public boolean check2Ships(Ship p ){

        for(ShipCell thisCell : shipCellList){
            for (ShipCell cell : p.getShipCellList()){
                if(thisCell.isSamePlace(cell)) return false;
                if(thisCell.isNeighbour(cell)) return false;
            }
        }
        return true;

    }

    public void surroundShip(FieldGrid playField ) {
        for(ShipCell shipCell : getShipCellList()){
            for(int dl=-1;dl<=1;dl++) {
                for (int dn = -1; dn <= 1; dn++) {
                    int letter = shipCell.getLetter()+dl;
                    int number = shipCell.getNumber()+dn;
                    try {

                        if(!hasCell(letter,number)){
                            FieldCell cell = playField.getFieldData().getCells()[letter][number];
                            playField.setImageToGridCell(cell, CSSpicture.MISS);
                        }
                    } catch (NullPointerException  | ArrayIndexOutOfBoundsException ignored){}

                }
            }
        }
    }



    private static boolean checkShipsArray(Ship[] ships , int size){

        for( int i=0;i<=size;i++ ){
            for( int j=0;j<=size;j++ ){
                if( i!=j && !( ships[i].check2Ships(ships[j]) ) ){
                    return false;
                }
            }
        }

        return true ;
    }//checkShipsArray





}//class ship

