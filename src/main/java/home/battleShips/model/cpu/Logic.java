package home.battleShips.model.cpu;

import home.battleShips.model.Game;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public interface Logic {

    Duration duration = Duration.seconds(2);
    int cycleCount    = 8;
    List<FadeTransition> animatedCells = new ArrayList<>();

    void setGame(Game game);
    void makeShot();

// TODO move animation to FieldGrid

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
