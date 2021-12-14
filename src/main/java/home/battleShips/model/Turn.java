package home.battleShips.model;


import home.battleShips.Main;


public class Turn {

    private final int FIELD_SIZE = Main.getFIELD_SIZE();

    private FieldCell cell;
    private TurnStatus status ;


    public Turn(FieldCell cell){
        this.cell = cell;
    }

//    public void shoot(FieldData fieldData){
//        fieldData.proceedTurn(this);
//
//        if(!isHit()) {
//            setStatus(TurnStatus.MISS);
//        }
//    }

    public boolean isHit(){

        return status == TurnStatus.HIT || status == TurnStatus.KILL;
    }
    public boolean isKill() {
        return status == TurnStatus.KILL;
    }

    public void killShip(){
        setStatus(TurnStatus.KILL);

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

