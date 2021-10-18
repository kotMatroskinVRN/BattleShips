package home.battleShips.model;

import home.battleShips.field.FieldCell;

public class Turn {

    private final FieldCell cell;
    private TurnStatus status   ;

    Turn(FieldCell cell){
        this.cell = cell;
        status = TurnStatus.MISS;
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

