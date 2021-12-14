package home.battleShips.model.cpu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HardestTest {

    private LogicTest logicTest ;




    @Test
    void setFieldData() {
    }

    @Test
    void getLastTurn() {
    }

    @Test
    void makeShot() {
        logicTest = new LogicTest();
        logicTest.setLogic( LogicFactory.HARDEST.getDifficulty() );
        System.out.println();
        System.out.println(this.getClass().getName());
        try{ logicTest.makeShot();}
        catch (IndexOutOfBoundsException e){e.printStackTrace();}

    }


}