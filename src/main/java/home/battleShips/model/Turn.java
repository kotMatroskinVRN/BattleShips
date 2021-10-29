package home.battleShips.model;

import home.battleShips.field.FieldCell;
import home.battleShips.field.FieldPicture;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.utils.StaticUtils;

public class Turn {

    private final int FIELD_SIZE = 11 ;

    private FieldCell cell;
    private TurnStatus status = TurnStatus.MISS;

    public Turn(FieldGrid fieldGrid){
        randomTurn(fieldGrid);
    }

    public Turn(FieldCell cell){
        this.cell = cell;
    }

    public void randomTurn(FieldGrid fieldGrid){
        int letter = (int)( Math.random()*(FIELD_SIZE-1) ) +1;
        int number = (int)( Math.random()*(FIELD_SIZE-1) ) +1;

        if(fieldGrid.getCells()[letter][number].getImage() == FieldPicture.SEA.getIMAGE()){
            cell = fieldGrid.getFieldData().getCells()[letter][number];
        }else{
            randomTurn(fieldGrid);
        }



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
}

