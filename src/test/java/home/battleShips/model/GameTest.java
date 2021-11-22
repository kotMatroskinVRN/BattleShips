package home.battleShips.model;

import home.battleShips.Controller;
import home.battleShips.field.grid.FieldGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private  FieldGrid playerField;
    private  FieldGrid cpuField;
    private  Controller controller;

    Game game ;

    @BeforeEach
    void setUp() {
        game = new Game(new Controller());

    }

    @Test
    void getPlayerField() {
    }

    @Test
    void getCpuField() {
    }

    @Test
    void killShip() {
    }

    @Test
    void setDifficulty() {
    }

    @Test
    void gameOver() {
    }
}