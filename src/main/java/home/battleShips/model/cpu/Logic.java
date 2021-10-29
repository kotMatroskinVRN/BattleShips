package home.battleShips.model.cpu;

import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.Game;
import home.battleShips.model.Ship;
import home.battleShips.model.Turn;
import javafx.application.Platform;

public interface Logic {


    Turn makeShot(Game game);



}
