package home.battleShips.model;

import java.util.HashSet;
import java.util.Set;

public class Ship{

    private final int size ;
    private final Set<FieldCell> shipCellList = new HashSet<>();
    private final Set<FieldCell> hitCells     = new HashSet<>();

    private boolean isVertical ;


    public Ship( int s , int li , int ni , char d ){

        if(s<1){
            System.out.printf("Ship %2d %c%d position is out of range of size \n" , s , li , ni );
        }

        // split to 2 sections : start position and end position
        if (d == 'v') {
            for (int i = 0; i < s; i++) {
                isVertical = true;
                FieldCell shipCell = new FieldCell(li,ni+i);
                shipCell.setDeckStyle(s,i);

                shipCellList.add( shipCell );


            }
        } else {
            for (int i = 0; i < s; i++) {
                isVertical = false;
                FieldCell shipCell = new FieldCell(li+i,ni);
                shipCell.setDeckStyle(s,i);
                shipCellList.add( shipCell );
            }
        }

        size = s;
    }

    public int getSize() {
        return size;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public Set<FieldCell> getShipCellList() {
        return shipCellList;
    }

    public Set<FieldCell> getHitCells() {
        return hitCells;
    }

    public boolean hasCell(int l , int n){
        for(FieldCell cell : shipCellList){
            if(cell.getLetter() == l && cell.getNumber() == n) return true;
        }
        return false;
    }

    public boolean hasCell(FieldCell fieldCell){
        int letter = fieldCell.getLetter();
        int number = fieldCell.getNumber();
        for(FieldCell cell : shipCellList){
            if(cell.getLetter() == letter && cell.getNumber() == number) return true;
        }
        return false;
    }

    public FieldCell getCell(FieldCell fieldCell){
        int letter = fieldCell.getLetter();
        int number = fieldCell.getNumber();
        for(FieldCell cell : shipCellList){
            if(cell.getLetter() == letter && cell.getNumber() == number) return cell;
        }
        return null;
    }

    public boolean hasHit(int l , int n){
        if(!hasCell(l,n)) return false;

        for(FieldCell cell : hitCells){
            if(cell.getLetter() == l && cell.getNumber() == n) return true;
        }

        return false;
    }

    public void addHit(int l , int n){
        if(hasCell(l,n)){
            hitCells.add( new FieldCell(l , n) );
        }
        else throw new IndexOutOfBoundsException();
    }

    public void addHit(FieldCell cell){
        if(hasCell(cell)){
            hitCells.add( cell );
        }
        else throw new IndexOutOfBoundsException();
    }

    public boolean isKilled(){
        return hitCells.size() >= size  ;
    }

    // check positions of THIS ship and given one
    public boolean check2Ships(Ship p ){

        for(FieldCell thisCell : shipCellList){
            for (FieldCell cell : p.getShipCellList()){
                if(thisCell.isSamePlace(cell)) return false;
                if(thisCell.isNeighbour(cell)) return false;
            }
        }
        return true;

    }



}

