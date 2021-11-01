package home.battleShips.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipCellTest {

    ShipCell cell;
    ShipCell samePlace;
    ShipCell neighbourRight;
    ShipCell neighbourLeft;
    ShipCell neighbourDown;
    ShipCell neighbourUp;
    ShipCell neighbourDiagNE;
    ShipCell neighbourDiagSE;
    ShipCell neighbourDiagSW;
    ShipCell neighbourDiagNW;
    ShipCell differPlace;


    @BeforeEach
    void setUp() {
        cell              = new ShipCell(1,5);
        samePlace         = new ShipCell(1,5);
        neighbourRight    = new ShipCell(2,5);
        neighbourLeft     = new ShipCell(0,5);
        neighbourDown     = new ShipCell(1,6);
        neighbourUp       = new ShipCell(1,4);
        neighbourDiagNE   = new ShipCell(2,4);
        neighbourDiagSE   = new ShipCell(2,6);
        neighbourDiagSW   = new ShipCell(0,6);
        neighbourDiagNW   = new ShipCell(0,4);
        differPlace       = new ShipCell(5,8);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getLetter() {
        assertEquals(1 , cell.getLetter());
    }

    @Test
    void getNumber() {
        assertEquals(5, cell.getNumber());
    }

    @Test
    void isSamePlace() {
        assertTrue(cell.isSamePlace(samePlace));

        assertFalse(cell.isSamePlace(neighbourLeft));
        assertFalse(cell.isSamePlace(neighbourRight));
        assertFalse(cell.isSamePlace(neighbourDown));
        assertFalse(cell.isSamePlace(neighbourUp));
        assertFalse(cell.isSamePlace(neighbourDiagNE));
        assertFalse(cell.isSamePlace(neighbourDiagNW));
        assertFalse(cell.isSamePlace(neighbourDiagSE));
        assertFalse(cell.isSamePlace(neighbourDiagSW));


    }

    @Test
    void isNeighbour() {

        assertTrue(cell.isNeighbour(neighbourDiagNE));
        assertTrue(cell.isNeighbour(neighbourDiagSE));
        assertTrue(cell.isNeighbour(neighbourDiagSW));
        assertTrue(cell.isNeighbour(neighbourDiagNW));
        assertTrue(cell.isNeighbour(neighbourRight));
        assertTrue(cell.isNeighbour(neighbourDown));
        assertTrue(cell.isNeighbour(neighbourUp));
        assertTrue(cell.isNeighbour(neighbourLeft));

        assertFalse(cell.isNeighbour(differPlace));
        assertFalse(cell.isNeighbour(samePlace));
    }
}