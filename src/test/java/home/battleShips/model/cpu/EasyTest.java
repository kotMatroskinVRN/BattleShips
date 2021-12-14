package home.battleShips.model.cpu;

import org.junit.jupiter.api.Test;

class EasyTest {


    @Test
    void setFieldData() {
    }

    @Test
    void getLastTurn() {
    }

    @Test
    void makeShot() {
        LogicTest logicTest = new LogicTest();
        logicTest.setLogic( LogicFactory.EASY.getDifficulty() );
        System.out.println();
        System.out.println(this.getClass().getName());
        logicTest.makeShot();

    }

}