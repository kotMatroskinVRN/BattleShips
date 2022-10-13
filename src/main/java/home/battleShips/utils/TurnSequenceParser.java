package home.battleShips.utils;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.Turn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TurnSequenceParser {

    private final int FIELD_SIZE = Main.getFIELD_SIZE();

    private final List<Turn> turns = new ArrayList<>();
    private String content;

    public TurnSequenceParser() {
    }

    public TurnSequenceParser(String name){
        content = name;
    }

    public void init(String name){
        content = name;
    }

    public void parse(){

        try(InputStream is = ClassLoader.getSystemResourceAsStream(content);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is , StandardCharsets.UTF_8) ))   {

            String line = reader.readLine();
            while(line != null){
                line = line.trim();
                if(!line.equals("")) {
                    int letter = StaticUtils.getNumberFromChar(line.split(" ")[0]);
                    int number = Integer.parseInt(line.split(" ")[1]);
                    if ( isCellInField(letter,number) ) {

                        FieldCell fieldCell = new FieldCell(letter, number);
                        Turn turn = new Turn(fieldCell);
                        turns.add(turn);
                    }
                }
                line = reader.readLine();
            }
        }
        catch (IOException e) { System.out.printf("%s  - %s\n" , content , "resource not found"  );
            System.exit(1);
            }



    }

    public List<Turn> getTurns() {
        return turns;
    }

    public static void main(String[] args) {

        TurnSequenceParser turnSequenceParser;
        turnSequenceParser = new TurnSequenceParser();
        turnSequenceParser.init("move_sequence/four.txt");
        turnSequenceParser.parse();
        System.out.println(turnSequenceParser.getTurns().size());
        System.out.println(turnSequenceParser.getTurns().get(23));
    }

    private boolean isCellInField(int letter , int number){
        return  letter>0 && letter<FIELD_SIZE && number < FIELD_SIZE && number > 0;
    }

}
