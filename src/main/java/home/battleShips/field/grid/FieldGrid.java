package home.battleShips.field.grid;

import home.battleShips.Language;
import home.battleShips.Main;
import home.battleShips.Translatable;
import home.battleShips.Translator;
import home.battleShips.model.*;
import home.battleShips.utils.BattleShipsLogger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FieldGrid extends GridPane implements Translatable {

    private final int FIELD_SIZE = Main.getFIELD_SIZE();
    private final FieldData fieldData;
    private final Button[][] buttons = new Button[Main.getFIELD_SIZE()][Main.getFIELD_SIZE()];
    private final Label[] letters = new Label[10];
    private Language language;

    private boolean isPlayerField;


    public FieldGrid(Language language){
        super();
        this.language = language;
        fieldData = new FieldData();
        setAlignment(Pos.CENTER);

        Translator.addSource(this);

        for(int l=0;l<FIELD_SIZE-1;l++){
            letters[l] = new Label();
            add(letters[l],l+1,0);
        }

    }

    public void setPlayerField(boolean isPlayerField){
        this.isPlayerField = isPlayerField;
    }

    public void init() {
        fieldData.init();
        defaultFill();
    }

    public void update(){
        for (FieldCell[] row : fieldData.getCells()){
            for (FieldCell cell : row){
                try {
                    Button button = getButton(cell);
                    button.setId(cell.getCssId().toString());
                    button.applyCss();

                }catch (NullPointerException ignore){}
            }
        }
    }

    public FieldData getFieldData() {
        return fieldData;
    }

    public Button getButton(FieldCell fieldCell){
        return buttons[fieldCell.getLetter()][fieldCell.getNumber()];
    }



    public void removeListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = fieldData.getCells()[l][n];
                getButton(cell).onMouseClickedProperty().set( null );
                getButton(cell).getStyleClass().removeAll();

            }
        }
    }

    public void showKilledShip(Ship ship) {
        BattleShipsLogger.getLogger().printVerbose("Killed Ship : " + ship);
        for(FieldCell shipCell : ship.getShipCellList()){
            int l = shipCell.getLetter();
            int n = shipCell.getNumber();
            FieldCell cell = fieldData.getCells()[l][n];

            CssId newCSS = shipCell.getCssId().getKilled();
            cell.setStyle(newCSS);


            if(isPlayerField) {
                if(newCSS == CssId.HIT_BACK)   cell.setStyle(CssId.HIT_FRONT);
                if(newCSS == CssId.HIT_FRONT)  cell.setStyle(CssId.HIT_BACK);
                Button button = getButton(shipCell);
                if(ship.isVertical()){
                    button.setRotate(270);
                }else{
                    button.setRotate(180);
                }

            }
        }
        update();
    }

    public void showShips() {
        for(Ship ship : getFieldData().getShips()){
            for(FieldCell shipCell : ship.getShipCellList()){
                int l = shipCell.getLetter();
                int n = shipCell.getNumber();
                FieldCell cell = fieldData.getCells()[l][n];
                cell.setStyle(shipCell.getCssId());
                Button button = getButton(cell);
                button.setId(cell.getCssId().toString());
                if(ship.isVertical()) {
                    button.setRotate(90);
                }

            }
        }
    }

    public void applyTurn(Turn turn){
        Button button =getButton(turn.getCell());
        button.setId(turn.getStatus().getPicture().toString());
        button.applyCss();
    }


    private void setLetters(){
//        Label blank = new Label(" ");
        for(int l=1;l<FIELD_SIZE;l++){
            String letter = "letter." + l ;
            Label label = letters[l-1];
            label.setText(language.getValue(letter));

        }
    }



    private void setNumbers(){

        for(int n=1;n<FIELD_SIZE;n++){
            int number = fieldData.getCells()[1][n].getNumber();
            Node label = new Label(String.valueOf(number));

            setConstraints(label , 0, n );

            getChildren().add(label);

        }
    }

    private void defaultFill(){
        for(char l=1; l<FIELD_SIZE;l++ ){
            for(int n=1; n<FIELD_SIZE; n++){

                Button button = new Button();
                button.setId(CssId.SEA.toString());
                button.applyCss();
                buttons[l][n] = button;

                setConstraints(button , l, n);
                getChildren().add(button);

            }
        }

        setLetters();
        setNumbers();
    }


    @Override
    public void updateText(Language language) {
        this.language = language;
        setLetters();
    }
}
