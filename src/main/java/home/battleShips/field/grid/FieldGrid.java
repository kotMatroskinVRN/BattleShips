package home.battleShips.field.grid;

import home.battleShips.field.FieldPicture;
import home.battleShips.field.FieldCell;
import home.battleShips.field.FieldData;
import home.battleShips.utils.StaticUtils;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class FieldGrid extends GridPane {

    private final int FIELD_SIZE = 11 ;

    private ImageView[][] cells = new ImageView[FIELD_SIZE][FIELD_SIZE];
    private FieldData fieldData;

    public FieldGrid(){
        super();
        fieldData = new FieldData();
        setAlignment(Pos.CENTER);

    }


    public void init() {

        long start;
        double time;

        fieldData.init();

        defaultFill();

    }

    public ImageView[][] getCells() {
        return cells;
    }

    public FieldData getFieldData() {
        return fieldData;
    }

    public void setImageToGridCell( FieldCell cell, FieldPicture picture) {
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        cell.setImage(picture);
        GridPane.setConstraints(cell.getImageView(), letter, number);
        getChildren().add(cell.getImageView());


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
                //System.out.println("defaultFill : " + arrayNumber);
                String letter = String.valueOf(c);

                ImageView imageView = fieldData.getImage(letter , n);

                setConstraints(imageView , arrayNumber, n);
                getChildren().add(imageView);

                cells[n][arrayNumber] = imageView;
            }
        }

        //data.addAll(Arrays.asList(cells));
        setLetters();
        setNumbers();
    }



}
