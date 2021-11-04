package home.battleShips.model.cpu;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.grid.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.*;
import home.battleShips.utils.StaticUtils;

import java.util.*;

public class Normal implements Logic {

    private final List<Turn> turns = new ArrayList<>();
    private final Stack<Turn> nextTurns = new Stack<>();
    private Game game;


    @Override
    public void setGame(Game game) {
        this.game = game;
    }


    @Override
    public void makeShot() {

        stopAnimation();

        FieldGrid cpuField = game.getCpuField();

        Turn turn;
        if(nextTurns.empty())  {
            turn = new Turn(cpuField);

            proceedTurn(turn);

        }
        else {

            System.out.println("next turns : " + nextTurns);
            proceedTurn(nextTurns.pop());
        }

    }

    private void proceedTurn(Turn turn){
        FieldGrid cpuField = game.getCpuField();
        FieldCell cell = turn.getCell();
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println("cpu:" + cell.getLetter()+number);

        for(Ship ship : game.getCpuField().getFieldData().getShips()){
            if( ship.hasCell(letter,number )){
                turn.setStatus(TurnStatus.HIT);
                turn.setShip(ship);
                ship.addHit(letter,number);

                fade(cell.getButton());
                cpuField.setGridCellStyle( cell, CSSpicture.HIT);
                turns.add(turn);


                finishHim();

                if(ship.isKilled()){
                    nextTurns.clear();
                    turn.setStatus(TurnStatus.KILL);
                    game.killShip(ship , cpuField);
                }
                break;
            }
        }




        if(turn.getStatus()==TurnStatus.MISS){
            fade(cell.getButton());
            cpuField.setGridCellStyle( cell, CSSpicture.MISS);
//            turns.add(turn);
        }else{
            makeShot();
        }
    }


    private void finishHim() {
        Turn hitTurn = turns.get(turns.size()-1);
        System.out.println(hitTurn);
        Ship hitShip = hitTurn.getShip();
        System.out.println(hitShip);
        List<ShipCell> shipHits = hitShip.getHitCells();

        if(nextTurns.empty())surroundHits(shipHits);

        if(hitShip.isKilled()) {
            nextTurns.clear();
            return;
        }

//        proceedTurn(nextTurns.pop());
        makeShot();

    }

    private void surroundHits(List<ShipCell> shipHits){
        pushHorisontalTurns(shipHits);
        pushVerticalTurns(shipHits);

    }
    private void pushVerticalTurns(List<ShipCell> shipHits){
        int letter;
        OptionalInt newNumber;
        FieldCell cell;

        letter = shipHits.get(0).getLetter();
        newNumber = shipHits.stream().mapToInt(ShipCell::getNumber).max();
        try {
            cell = game.getCpuField().getFieldData().getCells()[letter][newNumber.getAsInt()+1];
            nextTurns.push(new Turn( cell ));
            newNumber = shipHits.stream().mapToInt(ShipCell::getNumber).min();
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){}
        try {
            cell = game.getCpuField().getFieldData().getCells()[letter][newNumber.getAsInt()-1];
            nextTurns.push(new Turn( cell ));
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){}
    }
    private void pushHorisontalTurns(List<ShipCell> shipHits){
        int number;
        OptionalInt newLetter;
        FieldCell cell;

        number = shipHits.get(0).getNumber();
        newLetter = shipHits.stream().mapToInt(ShipCell::getLetter).max();
        try {
            cell = game.getCpuField().getFieldData().getCells()[newLetter.getAsInt() + 1][number];
            nextTurns.push(new Turn(cell));
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){}
        try {
            newLetter = shipHits.stream().mapToInt(ShipCell::getLetter).min();
            cell = game.getCpuField().getFieldData().getCells()[newLetter.getAsInt() - 1][number];
            nextTurns.push(new Turn( cell ));
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){}
    }




}

//TODO why cpu make turn twice after hit