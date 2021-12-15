package home.battleShips.model;


public class Turn {

    private final FieldCell cell;
    private TurnStatus status ;


    public Turn(FieldCell cell){
        this.cell = cell;
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

