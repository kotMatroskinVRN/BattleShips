package home.battleShips.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipCellTest {

    FieldCell cell;
    FieldCell samePlace;
    FieldCell neighbourRight;
    FieldCell neighbourLeft;
    FieldCell neighbourDown;
    FieldCell neighbourUp;
    FieldCell neighbourDiagNE;
    FieldCell neighbourDiagSE;
    FieldCell neighbourDiagSW;
    FieldCell neighbourDiagNW;
    FieldCell differPlace;


    @BeforeEach
    void setUp() {
        cell              = new FieldCell(2,5);
        samePlace         = new FieldCell(2,5);
        neighbourRight    = new FieldCell(3,5);
        neighbourLeft     = new FieldCell(1,5);
        neighbourDown     = new FieldCell(2,6);
        neighbourUp       = new FieldCell(1,4);
        neighbourDiagNE   = new FieldCell(3,4);
        neighbourDiagSE   = new FieldCell(3,6);
        neighbourDiagSW   = new FieldCell(1,6);
        neighbourDiagNW   = new FieldCell(1,4);
        differPlace       = new FieldCell(6,8);

    }



    @Test
    void getLetter() {
        assertEquals(2 , cell.getLetter());
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