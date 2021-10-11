package home.BattleShips.Field;

import javafx.scene.image.ImageView;
import org.w3c.dom.Node;

public interface PlayField {

    void init();


    ImageView[][] getCells();
}
