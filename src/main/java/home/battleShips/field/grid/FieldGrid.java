package home.battleShips.field.grid;

import home.battleShips.field.CSSpicture;
import home.battleShips.field.FieldPicture;
import home.battleShips.field.FieldCell;
import home.battleShips.field.FieldData;
import home.battleShips.utils.StaticUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class FieldGrid extends GridPane {

    private final int FIELD_SIZE = 11 ;

    private final Button[][] cells = new Button[FIELD_SIZE][FIELD_SIZE];
    private final FieldData fieldData;

    private int count_kills     = 0;

    public FieldGrid(){
        super();
        fieldData = new FieldData();
        setAlignment(Pos.CENTER);

    }


    public void init() {
        fieldData.init();
        defaultFill();
//        setR
    }

    public Button[][] getCells() {
        return cells;
    }

    public FieldData getFieldData() {
        return fieldData;
    }

//    public void setImageToGridCell( FieldCell cell, FieldPicture picture) {
//        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
//        int number = cell.getNumber();
//        cell.setImage(picture);
//        GridPane.setConstraints(cell.getImageView(), letter, number);
//        getChildren().add(cell.getImageView());
//        System.out.println("fieldgrid" + Thread.currentThread());
//
//    }

    public void setImageToGridCell( FieldCell cell, CSSpicture picture) {
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        cell.setStyle(picture);
        GridPane.setConstraints(cell.getButton(), letter, number);
        getChildren().add(cell.getButton());
        System.out.println("fieldgrid" + Thread.currentThread());

    }

    public int getCount_kills() {
        return count_kills;
    }

    public void addKill(){
        count_kills++;
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

                Button button = fieldData.getImage(letter , n);

                setConstraints(button , arrayNumber, n);
                getChildren().add(button);

                cells[n][arrayNumber] = button;
            }
        }

        setLetters();
        setNumbers();
    }



}
