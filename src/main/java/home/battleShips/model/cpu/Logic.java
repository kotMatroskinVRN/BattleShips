package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.utils.BattleShipsLogger;

import java.util.Stack;
import java.util.logging.Logger;

public interface Logic {

    BattleShipsLogger logger = BattleShipsLogger.getLogger();

    void setData(FieldData fieldData);
    void makeShot();
    Turn getLastTurn();



}
