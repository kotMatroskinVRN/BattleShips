package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import java.util.logging.Logger;

public interface Logic {

    Logger log = Main.getLog();

    void setData(FieldData fieldData);
    void makeShot();
    Turn getLastTurn();



}
