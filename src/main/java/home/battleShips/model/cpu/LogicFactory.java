package home.battleShips.model.cpu;

public enum LogicFactory {
    EASY("Легкий"){
        @Override
        public Logic getDifficulty() {
            return new Easy();
        }
    },
    NORMAL("Нормальный"){
        @Override
        public Logic getDifficulty() {
            return new Normal();
        }
    },
    HARD("Сложный"){
        @Override
        public Logic getDifficulty() {
            return new Hard();
        }
    },
    ULTIMATE("Максимальный"){
        @Override
        public Logic getDifficulty() {
            return new Ultimate();
        }
    },
    ;

    private final String description;


    LogicFactory(String name){
        description = name;
    }

    abstract public Logic getDifficulty();

    @Override
    public String toString() {
        return this.description;
    }
}
