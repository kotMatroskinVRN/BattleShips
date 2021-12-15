package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.FieldData;
import home.battleShips.model.Ship;
import home.battleShips.model.Turn;

import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class LogicTest {

    private final int RERUN_NUMBER = 1000;

    private int turnCount ;

    private FieldData fieldData;
    private Logic logic;
    private final List<Integer> gameResult = new ArrayList<>();
    private final Map<Integer, Integer> turnsTop = new HashMap<>();
    private final Set<Turn> turns = new HashSet<>();

    static{
        Main.getLog().setLevel(Level.SEVERE);
    }

    void setLogic(Logic logic){
        System.out.println();
        this.logic = logic;

    }




    void makeShot() {

        for(int i = 0; i< RERUN_NUMBER; i++) {
            setUp();


            while (fieldData.getCount_kills()<10) {
                turnCount++;
                counterAction();
            }
            gameResult.add(turnCount);
            addResultToMap(turnCount);
            //System.gc();
        }



        int maxShot = gameResult.stream().mapToInt(Integer::intValue).max().getAsInt();
        int minShot = gameResult.stream().mapToInt(Integer::intValue).min().getAsInt();
        int averageShot = (int) gameResult.stream().mapToInt(Integer::intValue).average().getAsDouble();


        System.out.println("Number of runs : " + RERUN_NUMBER);
        System.out.printf("Max     shots : %-5s\n" , maxShot  );
        System.out.printf("Min     shots : %-5s\n" , minShot  );
        System.out.printf("Average shots : %-5s\n" , averageShot );

        Map<Integer,Integer> top10 = turnsTop.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println(top10);

        int sumKeys = sumKeys(top10);
        int sumValues = sumValues(top10);

        System.out.printf("Sum TOP10 shots       : %-10s\n" , sumKeys  );
        System.out.printf("Sum TOP10 appearances : %-10s\n" , sumValues  );
        System.out.println();

    }

    private void setUp() {

        turnCount = 0;

        fieldData = new FieldData();
        fieldData.init();

//        showField();
//        System.out.println();
//        showShips();


//        StaticUtils.pause(2000);

        logic.setData(fieldData);
        turns.clear();



    }

    private int sumValues(Map<Integer, Integer> top10) {
        int result = 0;
        for(int key : top10.keySet()){
            result+=top10.get(key);
        }
        return result;

    }

    private int sumKeys(Map<Integer, Integer> top10) {
        int result = 0;
        for(int key : top10.keySet()){
            result+=key;
        }
        return result;
    }

    private void addResultToMap(int turnCount) {
        if(turnsTop.keySet().contains(turnCount)) {
            turnsTop.put(turnCount, turnsTop.get(turnCount) + 1);
        }else{
            turnsTop.put(turnCount, 1);
        }

    }


    private void counterAction() {
        logic.makeShot();
        Turn lastTurn = logic.getLastTurn();
//        if(turns.contains(lastTurn)){
        if(isTurnInTurns(lastTurn)){
            System.out.println("repetitive turn");
            System.out.println(lastTurn);
        }
        turns.add(lastTurn);
//        Turn lastTurn =  logic.getLastTurn();
//        System.out.printf("%-5s%-5s%s\n", turnCount , lastTurn.getCell() , lastTurn.getStatus().toString());

//            if(lastTurn.isKill()){
////            System.out.println(lastTurn.getShip().getSize());
//
//            }

    }

    private boolean isTurnInTurns(Turn lastTurn) {
        for(Turn turn : turns){
            if(turn.getCell().isSamePlace(lastTurn.getCell())){
                return true;
            }
        }
        return false;
    }


    private void showShips(){
        for(int l=1;l<Main.getFIELD_SIZE();l++){
            for(int n=1;n<Main.getFIELD_SIZE();n++){
                try{
                    boolean factor = false;
                    FieldCell cell = fieldData.getCells()[l][n];
                    for(Ship ship : fieldData.getShips()){
                        if(ship.hasCell(cell)) factor = true;
                    }
                    String symbol = factor? "*":"-";
                    System.out.print(symbol);
                }catch (NullPointerException ignored){}
            }

            System.out.println();
        }
    }



    private void showField(){
        for(int l=1;l<Main.getFIELD_SIZE();l++){
            for(int n=1;n<Main.getFIELD_SIZE();n++){
                try{
                    boolean factor = false;
                    FieldCell cell = fieldData.getCells()[l][n];
                    if(fieldData.isHit(cell)) factor=true;
                    String symbol = factor? "*":"-";
                    System.out.print(symbol);
                }catch (NullPointerException ignored){}
            }

            System.out.println();
        }
    }

}
