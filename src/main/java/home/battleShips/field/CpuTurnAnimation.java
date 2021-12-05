package home.battleShips.field;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CpuTurnAnimation {

    private final Duration DURATION = Duration.seconds(2);
    private final int CYCLE_COUNT    = 16;

    private List<FadeTransition> ANIMATED_CELLS = new ArrayList<>();

    public void fadeAnimation(Button button){
        FadeTransition animation = new FadeTransition( DURATION );

        ANIMATED_CELLS.add(animation);

        animation.setNode(button);
        animation.setFromValue(1.0);
        animation.setToValue(0.0);
        animation.setCycleCount(CYCLE_COUNT);
        animation.setAutoReverse(true);
        animation.play();
    }

    public void stopAnimation(){

        for (FadeTransition animation : ANIMATED_CELLS){

            animation.stop();
            animation.setFromValue(1.0);
            animation.setToValue(1.0);
            animation.setCycleCount(1);
            animation.play();
        }
        ANIMATED_CELLS.clear();

    }

}
