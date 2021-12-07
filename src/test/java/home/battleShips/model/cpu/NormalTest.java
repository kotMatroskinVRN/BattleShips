package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NormalTest {



    private LogicTest logicTest ;


    @BeforeEach
    void setUp() {
        logicTest = new LogicTest();
        logicTest.setUp( LogicFactory.NORMAL.getDifficulty() );

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