package home.battleShips.field;

import javafx.scene.control.Button;

public interface PlayField {

    void init();


    Button[][] getCells();
    FieldData getFieldData();
}
