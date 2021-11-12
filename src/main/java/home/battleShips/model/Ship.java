package home.battleShips.model;

import java.util.ArrayList;
import java.util.List;

public class Ship{


    static final int   FS = Game.FS ;


    private final int size ;

    private final List<ShipCell> shipCellList = new ArrayList<>();
    private final List<ShipCell> hitCells     = new ArrayList<>();


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
    }







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

//    public void surroundShip(FieldGrid playField ) {
//        for(ShipCell shipCell : getShipCellList()){
//            for(int dl=-1;dl<=1;dl++) {
//                for (int dn = -1; dn <= 1; dn++) {
//                    int letter = shipCell.getLetter()+dl;
//                    int number = shipCell.getNumber()+dn;
//                    try {
//
//                        if(!hasCell(letter,number)){
//                            FieldCell cell = playField.getFieldData().getCells()[letter][number];
//                            playField.setGridCellStyle(cell, CSSpicture.MISS);
//                        }
//                    } catch (NullPointerException  | ArrayIndexOutOfBoundsException ignored){}
//
//                }
//            }
//        }
//    }

}//class ship

