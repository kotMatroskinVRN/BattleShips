package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HardestTest {

    private LogicTest logicTest ;


    @BeforeEach
    void setUp() {
        logicTest = new LogicTest();
        logicTest.setUp( LogicFactory.HARDEST.getDifficulty() );

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
        try{ logicTest.makeShot();}
        catch (IndexOutOfBoundsException e){e.printStackTrace();}

    }


}