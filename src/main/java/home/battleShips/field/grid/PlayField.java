package home.battleShips.field.grid;

import javafx.scene.control.Button;

public interface PlayField {

    void init();


    Button[][] getCells();
    FieldData getFieldData();
}
