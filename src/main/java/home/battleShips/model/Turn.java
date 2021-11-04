package home.battleShips.model;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.grid.FieldCell;
import home.battleShips.field.grid.FieldGrid;

public class Turn {

    private final int FIELD_SIZE = 11 ;

    private FieldCell cell;
    private TurnStatus status = TurnStatus.MISS;
    private Ship ship;

    public Turn(FieldGrid fieldGrid){
        randomTurn(fieldGrid);
    }

    public Turn(FieldCell cell){
        this.cell = cell;
    }

    public void randomTurn(FieldGrid fieldGrid){
        int letter = (int)( Math.random()*(FIELD_SIZE-1) ) +1;
        int number = (int)( Math.random()*(FIELD_SIZE-1) ) +1;

        if(fieldGrid.getFieldData().getCells()[letter][number].getPicture()== CSSpicture.SEA){
            cell = fieldGrid.getFieldData().getCells()[letter][number];
        }else{
            randomTurn(fieldGrid);
        }



    }

    public Ship getShip() {
        return ship;
    }

    public FieldCell getCell() {
        return cell;
    }

    public TurnStatus getStatus() {
        return status;
    }

    public void setStatus(TurnStatus status) {
        this.status = status;
    }



    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public String toString() {
        return  cell.toString() ;
    }
}

