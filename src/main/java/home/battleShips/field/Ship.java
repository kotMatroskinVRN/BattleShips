package home.battleShips.field;

import home.battleShips.model.CssId;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Ship extends HBox {

    private final int size;
    private final Button[] buttons;
    private final boolean player;

    public Ship(int size , boolean player) {
        this.size = size;
        buttons = new Button[size];
        this.player = player;
    }

    public void init(){

        for(int i=0;i<size;i++){
            Button image = new Button();


            String cssID;
            if(player){
                cssID = getDeckStylePlayer(i);
                image.setRotate(180);
            }
            else{
                cssID = getDeckStyleCPU(i);
            }

            image.setId(cssID);
            buttons[i] = image;
        }

        getChildren().addAll(buttons);
    }

    public void setKilled(){
        for(Button button : buttons){
            CssId id = CssId.getID(button.getId()).getKilled();
            button.setId(id.toString());
            button.applyCss();
        }
    }

    private String getDeckStylePlayer(int number){
        if(size==1) return CssId.DECK_SINGLE.toString();
        if(number==0) return CssId.DECK_BACK.toString();
        if(number==size-1) return CssId.DECK_FRONT.toString();
        return CssId.DECK.toString();
    }

    private String getDeckStyleCPU(int number){
        if(size==1) return CssId.DECK_SINGLE.toString();
        if(number==0) return CssId.DECK_FRONT.toString();
        if(number==size-1) return CssId.DECK_BACK.toString();
        return CssId.DECK.toString();
    }
}
