package home.battleShips.model;


import home.battleShips.utils.StaticUtils;
import javafx.scene.control.Button;

public class Turn {

    private final int FIELD_SIZE = 11 ;

    private FieldCell cell;
    private TurnStatus status = TurnStatus.MISS;
    private Ship ship;

//    public Turn(FieldGrid fieldGrid){
//        randomTurn(fieldGrid);
//    }
    public Turn(FieldData fieldData){
        randomTurn(fieldData);
    }

    public Turn(FieldCell cell){
        this.cell = cell;
    }


    public void shoot(FieldData fieldData){
        for(Ship ship : fieldData.getShips()){
            if( ship.hasCell(cell)){
                ship.addHit(cell);
                setStatus(TurnStatus.HIT);
                setShip(ship);
            }
        }
        if(status==TurnStatus.MISS) {
            setStatus(TurnStatus.MISS);
        }
    }

    public boolean isHit(){
        return status == TurnStatus.HIT || status == TurnStatus.KILL;
    }


    public void killShip(){
        setStatus(TurnStatus.KILL);
    }

//    public void randomTurn(FieldGrid fieldGrid){
//        int letter = (int)( Math.random()*(FIELD_SIZE-1) ) +1;
//        int number = (int)( Math.random()*(FIELD_SIZE-1) ) +1;
//
//        if(fieldGrid.getFieldData().getCells()[letter][number].getPicture()== CSSpicture.SEA){
//            cell = fieldGrid.getFieldData().getCells()[letter][number];
//        }else{
//            randomTurn(fieldGrid);
//        }
//    }

    public void randomTurn(FieldData fieldData){
        int letter = (int)( Math.random()*(FIELD_SIZE-1) ) +1;
        int number = (int)( Math.random()*(FIELD_SIZE-1) ) +1;

        cell = fieldData.getCells()[letter][number];

        if(!fieldData.addTurnIfAbsent(this) ){
            randomTurn(fieldData);
        }

    }


    public Ship getShip() {
        return ship;
    }

    public FieldCell getCell() {
        return cell;
    }

    public TurnStatus getStatus() {
        return status;
    }

    public void setStatus(TurnStatus status) {
        this.status = status;
        Button button = getCell().getButton();
        button.setId(status.getStyleId());
        button.applyCss();
    }



    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public String toString() {
        return  cell.toString() ;
    }
}

