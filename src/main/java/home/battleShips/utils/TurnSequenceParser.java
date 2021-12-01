package home.battleShips.utils;

import home.battleShips.Main;
import home.battleShips.model.FieldCell;
import home.battleShips.model.Turn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TurnSequenceParser {

    private List<Turn> turns = new ArrayList<>();
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

        try{
            InputStream is = ClassLoader.getSystemResourceAsStream(content) ;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = reader.readLine();
            while(line != null){
                line = line.trim();
//                System.out.println(line);
                if(!line.equals("")) {
                    String letter = line.split(" ")[0];
                    int number = Integer.parseInt(line.split(" ")[1]);
                    if (!letter.equals("") && number < Main.getFIELD_SIZE() && number > 0) {

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

}
