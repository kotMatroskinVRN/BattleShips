package home.battleShips.model.cpu;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.grid.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.Game;
import home.battleShips.model.Ship;
import home.battleShips.model.Turn;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public interface Logic {

    Duration duration = Duration.seconds(2);
    int cycleCount    = 4;
    List<FadeTransition> animatedCells = new ArrayList<>();

    void setGame(Game game);
    void makeShot();



    default void fade(Button button){
        FadeTransition animation = new FadeTransition( duration );

        animatedCells.add(animation);

        animation.setNode(button);
        animation.setFromValue(1.0);
        animation.setToValue(0.1);
        animation.setCycleCount(cycleCount);
        animation.setAutoReverse(true);
        animation.play();
    }

    default void stopAnimation(){

        for (FadeTransition animation : animatedCells){

            animation.stop();
            animation.setFromValue(1.0);
            animation.setToValue(1.0);
            animation.setCycleCount(1);
            animation.play();
        }
        animatedCells.clear();

    }

}
