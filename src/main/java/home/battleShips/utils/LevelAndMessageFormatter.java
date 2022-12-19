package home.battleShips.utils;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LevelAndMessageFormatter extends Formatter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";


    @Override
    public String format(LogRecord record) {
        Level level = record.getLevel();
        String color = getColor(level);

        if(level.intValue()<=Level.INFO.intValue()){
            return color + record.getMessage() + ANSI_RESET +"\n";
        }

        String severity = record.getLevel().toString();
        if(severity.equals("SEVERE")){
            severity = "ERROR";
        }

        return String.format("%s%s : %s%s\n" , color , severity , record.getMessage() , ANSI_RESET );
    }

    private String getColor(Level level){

        if(level==Level.INFO) return ANSI_GREEN;
        if(level==Level.FINE) return ANSI_WHITE;
        if(level==Level.SEVERE) return ANSI_RED;
        if(level==Level.WARNING) return ANSI_YELLOW;
        return ANSI_RESET;
    }

}
