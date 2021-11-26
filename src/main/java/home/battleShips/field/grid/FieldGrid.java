package home.battleShips.field.grid;

import home.battleShips.Main;
import home.battleShips.model.FieldData;
import home.battleShips.utils.StaticUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FieldGrid extends GridPane {

    private final int FIELD_SIZE = Main.getFIELD_SIZE();
    private final FieldData fieldData;



    public FieldGrid(){
        super();
        fieldData = new FieldData();
        setAlignment(Pos.CENTER);
    }


    public void init() {
        fieldData.init();
        defaultFill();
    }

    public FieldData getFieldData() {
        return fieldData;
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

                Button button = fieldData.getButton(letter , n);

                setConstraints(button , arrayNumber, n);
                getChildren().add(button);

            }
        }

        setLetters();
        setNumbers();
    }




}
