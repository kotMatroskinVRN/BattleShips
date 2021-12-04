package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.model.TurnStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class LogicTest {


    private int turnCount ;

    private FieldData fieldData;
    private Logic logic;
    private final List<Integer> gameResult = new ArrayList<>();


    void setUp(Logic logic) {

        turnCount = 0;

        fieldData = new FieldData();
        fieldData.init();
        this.logic = logic;
        this.logic.setData(fieldData);


        Main.getLog().setLevel(Level.WARNING);
    }


    void makeShot() {

        for(int i=0;i<1000;i++) {
            setUp(this.logic);


            while (fieldData.getCount_kills()<10) {
                turnCount++;
                counterAction();
            }
            gameResult.add(turnCount);
            //System.gc();
        }

        System.out.println(this.getClass().getName());

        int maxShot = gameResult.stream().mapToInt(Integer::intValue).max().getAsInt();
        int minShot = gameResult.stream().mapToInt(Integer::intValue).min().getAsInt();
        int averageShot = (int) gameResult.stream().mapToInt(Integer::intValue).average().getAsDouble();



        System.out.printf("Max     shots : %-5s\n" , maxShot  );
        System.out.printf("Min     shots : %-5s\n" , minShot  );
        System.out.printf("Average shots : %-5s\n" , averageShot );

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
