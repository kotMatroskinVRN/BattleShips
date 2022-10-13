package home.battleShips.model.cpu;

import java.util.EnumSet;
import java.util.ResourceBundle;

public enum LogicFactory {
    EASY("difficulty.easy"){
        @Override
        public Logic getDifficulty() {
            return new LogicEasy();
        }
    },
    NORMAL("difficulty.normal"){
        @Override
        public Logic getDifficulty() {
            return new LogicNormal();
        }
    },
    HARD("difficulty.hard"){
        @Override
        public Logic getDifficulty() {
            return new LogicHard();
        }
    },
    ULTIMATE("difficulty.ultimate"){
        @Override
        public Logic getDifficulty() {
            return new LogicUltimate();
        }
    },
    HARDEST("difficulty.hardest"){
        @Override
        public Logic getDifficulty() {
            return new LogicHardest();
        }
    },
    ;

    private  String description;
    private final String key;
    private static ResourceBundle resourceBundle;

    LogicFactory(String name){
        key = name;
//        description = resourceBundle.getString(key);
    }


    abstract public Logic getDifficulty();

    public static void updateDescription(ResourceBundle resourceBundle){

        for(LogicFactory logicFactory : EnumSet.allOf(LogicFactory.class)){
//            final String description = logicFactory.description;
            logicFactory.description = resourceBundle.getString(logicFactory.key);
        }
    }

    public static LogicFactory getLogic(String description ){

        for(LogicFactory logicFactory : EnumSet.allOf(LogicFactory.class)){
            if(description.equals(logicFactory.description)) return logicFactory;
        }
        return null;
    }

    @Override
    public String toString() {
        return description;
    }
}
