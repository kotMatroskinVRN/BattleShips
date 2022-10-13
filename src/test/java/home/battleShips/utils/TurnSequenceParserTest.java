package home.battleShips.utils;

import home.battleShips.model.FieldCell;
import home.battleShips.model.Turn;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnSequenceParserTest  {

    private TurnSequenceParser turnSequenceParser;

    @BeforeEach
    void setUp() {
        turnSequenceParser = new TurnSequenceParser();
        turnSequenceParser.init("move_sequence/four.txt");
        turnSequenceParser.parse();
    }

    @Test
    void init() {
        System.out.println(System.getProperty("user.dir"));
    }



    @Test
    void parse() {

        assertNotNull( turnSequenceParser.getTurns() );
        assertTrue( turnSequenceParser.getTurns().size() > 0 );
    }

    @Test
    void getTurns() {
        Turn turn = new Turn( new FieldCell(10 , 7));
        assertEquals( turn.toString() ,turnSequenceParser.getTurns().get(23).toString() );

    }
}