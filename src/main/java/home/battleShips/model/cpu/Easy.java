package home.battleShips.model.cpu;

import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;

public class Easy implements Logic {
    private Turn lastTurn;
    private FieldData fieldData ;

    @Override
    public void setData(FieldData fieldData) {
        this.fieldData = fieldData;
    }

    @Override
    public void makeShot() {

        lastTurn = new Turn(fieldData);

        lastTurn.shoot(fieldData);
            if(lastTurn.isHit()){

                // action when hit

                if(lastTurn.getShip().isKilled()){
                    lastTurn.killShip();
                }
            }
    }

    @Override
    public Turn getLastTurn() {
        return lastTurn;
    }
}
