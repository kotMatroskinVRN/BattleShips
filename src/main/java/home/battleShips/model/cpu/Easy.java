package home.battleShips.model.cpu;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.grid.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.model.Game;
import home.battleShips.model.Ship;
import home.battleShips.model.Turn;
import home.battleShips.model.TurnStatus;
import home.battleShips.utils.StaticUtils;

public class Easy implements Logic {
    private Game game;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void makeShot() {

        stopAnimation();

        FieldGrid cpuField = game.getCpuField();
        Turn turn = new Turn(cpuField);

        FieldCell cell = turn.getCell();
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println("cpu:" + cell.getLetter()+number);

        for(Ship ship : game.getCpuField().getFieldData().getShips()){
            if( ship.hasCell(letter,number )){
                turn.setStatus(TurnStatus.HIT);
                ship.addHit(letter,number);

                fade(cell.getButton());
                cpuField.setGridCellStyle( cell, CSSpicture.HIT);

                if(ship.isKilled()){
                    turn.setStatus(TurnStatus.KILL);
                    game.killShip(ship , cpuField);
                }
                break;
            }
        }

        if(turn.getStatus()==TurnStatus.MISS){
            fade(cell.getButton());
            cpuField.setGridCellStyle( cell, CSSpicture.MISS);
        }else{
            makeShot();
        }

    }
}
