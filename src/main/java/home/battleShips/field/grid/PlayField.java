package home.battleShips.field.grid;

import home.battleShips.model.FieldData;
import javafx.scene.control.Button;

public interface PlayField {

    void init();


    Button[][] getCells();
    FieldData getFieldData();
}
