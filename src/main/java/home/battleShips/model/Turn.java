package home.battleShips.model;


import home.battleShips.Main;


public class Turn {

    private final int FIELD_SIZE = Main.getFIELD_SIZE();

    private FieldCell cell;
    private TurnStatus status ;

//    public Turn(FieldData fieldData){
//        randomTurn(fieldData);
////        fieldData.addTurn(this);
//    }

    public Turn(FieldCell cell){
        this.cell = cell;
    }

    public void shoot(FieldData fieldData){
        if(fieldData.isHit(cell)){
            fieldData.addHit(cell);
            setStatus(TurnStatus.HIT);
        }
        if(fieldData.isShipKilled(cell)){
            killShip();

        }
        if(!isHit()) {
            setStatus(TurnStatus.MISS);
        }
    }

    public boolean isHit(){

        return status == TurnStatus.HIT || status == TurnStatus.KILL;
    }
    public boolean isKill() {
        return status == TurnStatus.KILL;
    }

    public void killShip(){
        setStatus(TurnStatus.KILL);

    }

    public void randomTurn(FieldData fieldData){
        int letter = (int)( Math.random()*(FIELD_SIZE-1) ) +1;
        int number = (int)( Math.random()*(FIELD_SIZE-1) ) +1;

        cell = fieldData.getCells()[letter][number];

//        if(!fieldData.addTurnIfAbsent(this) ){
        if(fieldData.isCellInTurns(cell) ){
            randomTurn(fieldData);
        }

    }



    public FieldCell getCell() {
        return cell;
    }

    public TurnStatus getStatus() {
        return status;
    }

    public void setStatus(TurnStatus status) {
        this.status = status;
        cell.setStyle( status.getPicture() );
    }


    @Override
    public String toString() {
        return  cell.toString() ;
    }



}

