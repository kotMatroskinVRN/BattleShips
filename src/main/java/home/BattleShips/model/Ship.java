package home.BattleShips.model;

import java.util.ArrayList;
import java.util.List;

public class Ship{


    static final int   FS = Game.FS ;


    private final int size ;

    private final List<ShipCell> shipCellList = new ArrayList<>();
    private final List<ShipCell> hitCells     = new ArrayList<>();

    private boolean 		alive  ;

    Ship( int s , int li , int ni , char d ){

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
        hitCells.add( new ShipCell(l , n) );
    }

    public boolean isKilled(){
        return hitCells.size() >= size  ;
    }

    // check positions of THIS ship and given one
    public boolean check2Ships(Ship p ){
        boolean flag = true ;

        for(ShipCell thisCell : shipCellList){
            if(!flag) return flag;
            for (ShipCell cell : p.getShipCellList()){
                if(!flag) return flag;

                if(thisCell.isSamePlace(cell)) flag = false;

                if(thisCell.isNeighbour(cell)) flag = false;

            }
        }
        return flag;

    }









}//class ship

