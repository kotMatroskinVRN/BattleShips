package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HardTest {



    @Test
    void setFieldData() {
    }

    @Test
    void getLastTurn() {
    }

    @Test
    void makeShot() {
        LogicTest logicTest = new LogicTest();
        logicTest.setLogic( LogicFactory.HARD.getDifficulty() );
        System.out.println(this.getClass().getName());
        logicTest.makeShot();

    }


}