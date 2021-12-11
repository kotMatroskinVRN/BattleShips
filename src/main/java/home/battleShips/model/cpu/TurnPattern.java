package home.battleShips.model.cpu;

import home.battleShips.Main;
import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.utils.TurnSequenceParser;

import java.util.ArrayList;
import java.util.List;

public enum TurnPattern {

    ULTIMATE_FIRST("move_sequence/four_three.txt"){},
    ULTIMATE_SECOND("move_sequence/four_three_center.txt"){},
    FOURS("move_sequence/four.txt"){},
    TWOS("move_sequence/two.txt"){},
    THREES("move_sequence/threes.txt"){},

    RANDOM(){

        @Override
        public Turn getTurn() {
            int letter = (int) (Math.random() * (Main.getFIELD_SIZE() - 1)) + 1;
            int number = (int) (Math.random() * (Main.getFIELD_SIZE() - 1)) + 1;

            return new Turn(fieldData.getCells()[letter][number]);

        }
    },
    ;
    private List<Turn> initPattern;
    private List<Turn> turnPattern;
    private static FieldData fieldData;

    TurnPattern() {
        initPattern = new ArrayList<>();
    }

    TurnPattern(String fileName) {
        TurnSequenceParser turnSequenceParser;
        turnSequenceParser = new TurnSequenceParser(fileName);
        turnSequenceParser.parse();
        initPattern = turnSequenceParser.getTurns();
    }

    public void init(){
        turnPattern = new ArrayList<>(initPattern);
    }

    public Turn getTurn(){
        if(turnPattern.size()==0) return null;
//        System.out.println(this.name() + " " + turnPattern.size());
        int element = (int) (Math.random() * (turnPattern.size()));
//        System.out.println(element);
        Turn turn = turnPattern.get(element);
        turnPattern.remove(turn);

        while(fieldData.isCellInTurns(turn.getCell())){
            if(turnPattern.size()==0) return null;
            element = (int) (Math.random() * (turnPattern.size()));
//            System.out.println(element);
            turn = turnPattern.get(element);
            turnPattern.remove(turn);
        }

        return turn;
    }

    public boolean isEmpty(){
        return turnPattern.size()==0;
    }


    public static void setFieldData(FieldData data){
        fieldData = data;
    }

    public FieldData getFieldData(){
        return fieldData;
    }

}
