package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HardTest {

    private LogicTest logicTest ;


    @BeforeEach
    void setUp() {
        logicTest = new LogicTest();
        logicTest.setUp( new Hard() );

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