package home.battleShips.field;

import javafx.scene.layout.FlowPane;

public class ShipBar extends FlowPane {

    private final Ship[] ships = new Ship[4];
    private final boolean player;

    public ShipBar(boolean player){
        super();

        this.player = player;
        if(player){
            for(int i=0; i<ships.length; i++){
                ships[i] = new Ship(ships.length-i, true);
            }
        }else{
            for(int i=0; i<ships.length; i++){
                ships[i] = new Ship(i+1, false);
            }
        }

    }

    public void killCarrier(){
        if(player) ships[0].setKilled();
        else ships[ships.length-1-0].setKilled();
    }

    public void killBattleShip(){
        if(player) ships[1].setKilled();
        else ships[ships.length-1-1].setKilled();
    }

    public void killDestroer(){
        if(player) ships[2].setKilled();
        else ships[ships.length-1-2].setKilled();
    }

    public void killTorpedoBoat(){
        if(player) ships[3].setKilled();
        else ships[ships.length-1-3].setKilled();
    }

    public void init(){
        for(Ship ship : ships){
            ship.init();
        }
        getChildren().addAll(ships);
    }

}
