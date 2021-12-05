package home.battleShips.field.grid;

import home.battleShips.Main;
import home.battleShips.model.*;
import home.battleShips.utils.StaticUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FieldGrid extends GridPane {

    private final int FIELD_SIZE = Main.getFIELD_SIZE();
    private final FieldData fieldData;
    private final Button[][] buttons = new Button[Main.getFIELD_SIZE()][Main.getFIELD_SIZE()];



    public FieldGrid(){
        super();
        fieldData = new FieldData();
        setAlignment(Pos.CENTER);
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
        return buttons[StaticUtils.getNumberFromChar(fieldCell.getLetter())][fieldCell.getNumber()];
    }

    public void setListeners(Game game) {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = getFieldData().getCells()[l][n];
                getButton(cell).onMouseClickedProperty().set( ae -> game.turn( cell ) );
                getButton(cell).getStyleClass().add("player");
            }
        }
    }

    public void removeListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = getFieldData().getCells()[l][n];
                getButton(cell).onMouseClickedProperty().set( null );
                getButton(cell).getStyleClass().removeAll();

            }
        }
    }

    public void showKilledShip(Ship ship) {
        for(ShipCell shipCell : ship.getShipCellList()){
            int l = shipCell.getLetter();
            int n = shipCell.getNumber();
            FieldCell cell = getFieldData().getCells()[l][n];
            CssId newCSS = cell.getCssId().getAfterKill();
            cell.setStyle(newCSS);
        }
        update();
    }

    public void showShips() {
        for(Ship ship : getFieldData().getShips()){
            for(ShipCell shipCell : ship.getShipCellList()){
                int l = shipCell.getLetter();
                int n = shipCell.getNumber();
                FieldCell cell = getFieldData().getCells()[l][n];
                cell.setStyle(shipCell.getCssId());
                getButton(cell).setId(cell.getCssId().toString());

            }
        }
    }

    public void applyTurn(Turn turn){

        Button button =getButton(turn.getCell());
        button.setId(turn.getStatus().getPicture().toString());
        button.applyCss();
    }


    private void setLetters(){
        for(int l=1;l<FIELD_SIZE;l++){
            String letter = fieldData.getCells()[l][1].getLetter();
            Node label = new Label(letter);
            setConstraints(label , l, 0);
            getChildren().add(label);
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
        for(char c='А'; c <= 'К' ;c++ ){
            for(int n=1; n<FIELD_SIZE; n++){

                if(c=='Й') continue;
                int arrayNumber= StaticUtils.getNumberFromChar(c);
                String letter = String.valueOf(c);

                //Button button = fieldData.getButton(letter , n);
                Button button = new Button();
                button.setId(CssId.SEA.toString());
                button.applyCss();
                buttons[arrayNumber][n] = button;
                setConstraints(button , arrayNumber, n);
                getChildren().add(button);

            }
        }

        setLetters();
        setNumbers();
    }



}
