package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

class HardTest {

    private int turnCount;

    private FieldData fieldData;
    private Logic logic;
    private final List<Integer> gameResult = new ArrayList<>();

    @BeforeEach
    void setUp() {

        turnCount = 0;

        fieldData = new FieldData();
        fieldData.init();
        logic = new Hard();
        logic.setData(fieldData);


        Main.getLog().setLevel(Level.WARNING);
    }

    @Test
    void setFieldData() {
    }

    @Test
    void getLastTurn() {
    }

    @Test
    void makeShot() {

        for(int i=0;i<1000;i++) {
            setUp();


            while (fieldData.getCount_kills()<10) {
                turnCount++;
                counterAction();
            }
            gameResult.add(turnCount);
            //System.gc();
        }

        System.out.println(this.getClass().getName());

        int max = gameResult.stream().mapToInt(Integer::intValue).max().getAsInt();
        int min = gameResult.stream().mapToInt(Integer::intValue).min().getAsInt();
        int average = (int) gameResult.stream().mapToInt(Integer::intValue).average().getAsDouble();

        System.out.printf("Max     shots : %s\n" , max);
        System.out.printf("Min     shots : %s\n" , min);
        System.out.printf("Average shots : %s\n" , average);

    }


    private void counterAction() {
        logic.makeShot();
        Turn lastTurn =  logic.getLastTurn();
//        System.out.printf("%-5s%-5s%s\n", turnCount , lastTurn.getCell() , lastTurn.getStatus().toString());
        if(lastTurn.isKill()){
//            System.out.println(lastTurn.getShip().getSize());

        }

    }


}