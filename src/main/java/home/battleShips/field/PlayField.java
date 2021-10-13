package home.battleShips.field;

import javafx.scene.image.ImageView;

public interface PlayField {

    void init();


    ImageView[][] getCells();
    FieldData getFieldData();
}
