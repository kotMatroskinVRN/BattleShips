package home.battleShips.model;

import home.battleShips.utils.StaticUtils;

import java.util.ArrayList;
import java.util.List;

public class Ship{

    private final int size ;
    private final List<ShipCell> shipCellList = new ArrayList<>();
    private final List<ShipCell> hitCells     = new ArrayList<>();


    public Ship( int s , int li , int ni , char d ){

        if(s<1){
            System.out.printf("Ship %2d %c%d position is out of range of size \n" , s , li , ni );
        }

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

    public boolean hasCell(FieldCell fieldCell){
        int letter = StaticUtils.getNumberFromChar(fieldCell.getLetter());
        int number = fieldCell.getNumber();
        for(ShipCell cell : shipCellList){
            if(cell.getLetter() == letter && cell.getNumber() == number) return true;
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

    public void addHit(FieldCell cell){
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        if(hasCell(letter,number)){
            hitCells.add( new ShipCell(letter , number) );
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



}

