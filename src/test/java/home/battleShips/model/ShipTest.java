package home.battleShips.model;

import home.battleShips.field.FieldPicture;
import home.battleShips.field.grid.FieldGrid;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    Ship ship41;
    Ship ship31;
    Ship ship32;
    Ship ship21;
    Ship ship22;
    Ship ship23;
    Ship ship11;
    Ship ship12;
    Ship ship13;
    Ship ship14;

    @BeforeEach
    void setUp() {

        ship41 = new Ship(4,2,5,'h');
        ship31 = new Ship(3,2,1,'v');
        ship32 = new Ship(3,7,3,'h');
        ship21 = new Ship(2,8,1,'h');
        ship22 = new Ship(2,3,8,'h');
        ship23 = new Ship(2,7,5,'v');
        ship11 = new Ship(1,0,0,'h');
        ship12 = new Ship(1,0,6,'h');
        ship13 = new Ship(1,6,0,'h');
        ship14 = new Ship(1,5,3,'h');
    }

    @Test
    void randomSetOfShips() {
        Ship[] ships = Ship.randomSetOfShips();

        for(int i=0;i<10;i++){
            assertNotNull(ships[i]);
        }

    }

    @Test
    void getShipCellList() {
        assertEquals( 4 , ship41.getShipCellList().size() );
        assertEquals( 3 , ship31.getShipCellList().size() );
        assertEquals( 3 , ship32.getShipCellList().size() );
        assertEquals( 2 , ship21.getShipCellList().size() );
        assertEquals( 2 , ship22.getShipCellList().size() );
        assertEquals( 2 , ship23.getShipCellList().size() );
        assertEquals( 1 , ship11.getShipCellList().size() );
        assertEquals( 1 , ship12.getShipCellList().size() );
        assertEquals( 1 , ship13.getShipCellList().size() );
        assertEquals( 1 , ship14.getShipCellList().size() );
    }

    @Test
    void getHitCells() {

        //TODO release
    }

    @Test
    void hasCell() {
        assertTrue( ship41.hasCell(4,5) );
    }

    @Test
    void hasHit() {
        //TODO release
    }

    @Test
    void addHit() {
        ship41.addHit(3,5);
        assertTrue( ship41.hasHit(3,5) );
        assertTrue( ship41.hasCell(3,5) );

    }

    @Test
    void isKilled() {

        assertThrows( IndexOutOfBoundsException.class , () -> ship14.addHit(5,2) );

        ship14.addHit(5,3);


    }

    @Test
    void check2Ships() {

        assertTrue( ship41.check2Ships(ship13) );

        assertFalse( ship11.check2Ships(  new Ship(1,1,0,'h') ) );
        assertFalse( ship11.check2Ships(  new Ship(1,0,0,'h') ) );

    }

    @Test
    void surroundShip() {

//        FieldGrid fieldGrid = new FieldGrid();
//        fieldGrid.init();
//        Ship ship = fieldGrid.getFieldData().getShips()[0];
//        ship.surroundShip(fieldGrid);
//
//        //Set<ShipCell> shipCells = new HashSet<>();
//
//
//        for (ShipCell shipCell : ship.getShipCellList()){
//
//            Image image = fieldGrid.getCells()[shipCell.getLetter()+1][shipCell.getNumber()+1].getImage();
//            assertEquals(FieldPicture.MISS , image);
//            break;
//
//        }




        //TODO solve how to test GUI method

    }
}