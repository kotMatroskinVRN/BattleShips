package home.battleShips.model.cpu;

import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;

public class LogicEasy implements Logic {
    private Turn lastTurn;
    private FieldData fieldData ;

    private TurnPattern pattern;

    @Override
    public void setData(FieldData fieldData) {
        this.fieldData = fieldData;
        TurnPattern.setFieldData(fieldData);

        pattern = TurnPattern.RANDOM;
        pattern.init();
    }



    @Override
    public void makeShot() {

        Turn turn = pattern.getTurn(); // random turn
        while(fieldData.isCellInTurns(turn.getCell())) {
            turn = pattern.getTurn(); // random turn
        }

        lastTurn = turn;

        fieldData.proceedTurn(turn);

    }

    @Override
    public Turn getLastTurn() {
        return lastTurn;
    }
}
