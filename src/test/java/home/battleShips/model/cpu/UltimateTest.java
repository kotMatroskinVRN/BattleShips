package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UltimateTest {

    private LogicTest logicTest ;


    @BeforeEach
    void setUp() {
        logicTest = new LogicTest();
        logicTest.setUp( LogicFactory.ULTIMATE.getDifficulty() );

    }

    @Test
    void setFieldData() {
    }

    @Test
    void getLastTurn() {
    }

    @Test
    void makeShot() {
        System.out.println(this.getClass().getName());
        logicTest.makeShot();

    }


}