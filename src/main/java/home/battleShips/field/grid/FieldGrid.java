package home.battleShips.field.grid;

import home.battleShips.field.FieldData;
import home.battleShips.field.PlayField;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class FieldGrid extends GridPane implements PlayField {

    private final int FIELD_SIZE = 11 ;

    private ImageView[][] cells = new ImageView[FIELD_SIZE][FIELD_SIZE];
    private FieldData fieldData;

    public FieldGrid(){
        super();
        fieldData = new FieldData();
        setAlignment(Pos.CENTER);

    }

    @Override
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

    private void setLetters(){
        for(int l=1;l<FIELD_SIZE;l++){
            String letter = fieldData.getCells()[1][l].getLetter();
            Node label = new Label(letter);
            setConstraints(label , l, 0);
            getChildren().add(label);
        }
    }
    private void setNumbers(){
        for(int n=1;n<FIELD_SIZE;n++){
            int number = fieldData.getCells()[n][1].getNumber();
            Node label = new Label(String.valueOf(number));
            setConstraints(label , 0, n );
            getChildren().add(label);
        }
    }

    private void defaultFill(){
        for(char c='А'; c <= 'К' ;c++ ){
            for(int n=1; n<FIELD_SIZE; n++){

                if(c=='Й') continue;
                int arrayNumber= getNumberFromChar(c);
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

    private int getNumberFromChar(char c){
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }

}
