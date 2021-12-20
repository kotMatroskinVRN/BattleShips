package home.battleShips.field;

import home.battleShips.model.ShipClass;
import javafx.scene.layout.FlowPane;

import java.util.EnumSet;

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

    public void init(){
        for(Ship ship : ships){
            ship.init();
        }
        getChildren().addAll(ships);
    }

    public void killShips(EnumSet<ShipClass> set){
        for(ShipClass entry : set){
            if(player) {
                ships[4-entry.getSize()].setKilled();
            }
            else {
                ships[entry.getSize()-1].setKilled();
            }
        }
    }

}
