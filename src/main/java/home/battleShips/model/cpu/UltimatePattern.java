package home.battleShips.model.cpu;

import home.battleShips.model.FieldData;
import home.battleShips.model.Turn;
import home.battleShips.utils.TurnSequenceParser;

import java.util.List;

public enum UltimatePattern {

    FIRST("move_sequence/four_three.txt"){},
    SECOND("move_sequence/four_three_center.txt"){},
    RANDOM(){

        @Override
        public Turn getTurn(){
            return new Turn(fieldData);
        }
    },
    ;

    private List<Turn> turnPattern;
    FieldData fieldData;

    UltimatePattern() {
    }

    UltimatePattern(String fileName) {
        TurnSequenceParser turnSequenceParser;
        turnSequenceParser = new TurnSequenceParser(fileName);
        turnSequenceParser.parse();
        turnPattern = turnSequenceParser.getTurns();
    }

    public Turn getTurn(){
        System.out.println(this.name() + " " + turnPattern.size());
        int element = (int) (Math.random() * (turnPattern.size()));
        System.out.println(element);
        Turn turn = turnPattern.get(element);
        turnPattern.remove(turn);


        return turn;
    }

    public boolean isEmpty(){
        return turnPattern.size()==0;
    }


    public void setFieldData(FieldData data){
        fieldData = data;
    }

}
