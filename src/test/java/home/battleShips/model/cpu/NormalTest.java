package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NormalTest {


    @Test
    void setFieldData() {
    }

    @Test
    void getLastTurn() {
    }

    @Test
    void makeShot() {
        LogicTest logicTest = new LogicTest();
        logicTest.setLogic( LogicFactory.NORMAL.getDifficulty() );
        System.out.println(this.getClass().getName());
        logicTest.makeShot();

    }



}