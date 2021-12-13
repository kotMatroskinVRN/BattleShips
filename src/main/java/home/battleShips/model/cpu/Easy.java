package home.battleShips.model.cpu;

import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;

public class Easy implements Logic {
    private Turn lastTurn;
    private FieldData fieldData ;

    private TurnPattern pattern;

    @Override
    public void setData(FieldData fieldData) {
        this.fieldData = fieldData;
        pattern = TurnPattern.RANDOM;
    }

//    @Override
//    public FieldData getData() {
//        return null;
//    }

    @Override
    public void makeShot() {

        Turn turn = pattern.getTurn(); // random turn
        while(fieldData.isCellInTurns(turn.getCell())) {
            turn = pattern.getTurn(); // random turn
        }
        fieldData.addTurn(turn);
        lastTurn = turn;

        lastTurn.shoot(fieldData);

    }

    @Override
    public Turn getLastTurn() {
        return lastTurn;
    }
}
