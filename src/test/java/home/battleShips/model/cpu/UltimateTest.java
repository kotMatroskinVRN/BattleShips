package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UltimateTest {


//    @BeforeEach
//    void setUp() {
//
//
//    }

    @Test
    void setFieldData() {
    }

    @Test
    void getLastTurn() {
    }

    @Test
    void makeShot() {
        LogicTest logicTest = new LogicTest();
        logicTest.setLogic( LogicFactory.ULTIMATE.getDifficulty() );
        System.out.println(this.getClass().getName());
        try{ logicTest.makeShot();}
        catch (IndexOutOfBoundsException e){e.printStackTrace();}

    }


}