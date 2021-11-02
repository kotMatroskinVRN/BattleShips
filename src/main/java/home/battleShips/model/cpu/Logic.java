package home.battleShips.model.cpu;

import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.Game;
import home.battleShips.model.Ship;
import home.battleShips.model.Turn;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.util.Duration;

public interface Logic {

    Duration duration = Duration.seconds(2);


    void makeShot(Game game);

    default void fade(Button button){
        FadeTransition animation = new FadeTransition( duration );
//        animation.jumpTo(Duration.seconds(2));
        animation.setNode(button);
        animation.setFromValue(1.0);
        animation.setToValue(0.1);
        animation.setCycleCount(4);
        animation.setAutoReverse(true);
        animation.play();
    }

}
