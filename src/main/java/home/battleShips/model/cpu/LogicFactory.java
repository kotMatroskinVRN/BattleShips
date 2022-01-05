package home.battleShips.model.cpu;

import home.battleShips.field.Skin;

import java.util.EnumSet;
import java.util.ResourceBundle;

public enum LogicFactory {
    EASY("difficulty.easy"){
        @Override
        public Logic getDifficulty() {
            return new Easy();
        }
    },
    NORMAL("difficulty.normal"){
        @Override
        public Logic getDifficulty() {
            return new Normal();
        }
    },
    HARD("difficulty.hard"){
        @Override
        public Logic getDifficulty() {
            return new Hard();
        }
    },
    ULTIMATE("difficulty.ultimate"){
        @Override
        public Logic getDifficulty() {
            return new Ultimate();
        }
    },
    HARDEST("difficulty.hardest"){
        @Override
        public Logic getDifficulty() {
            return new Hardest();
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
    @Override
    public String toString() {
        return description;
    }
}
