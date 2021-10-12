package home.BattleShips.Field;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

import java.util.Arrays;

public class FieldData {

    private final int FIELD_SIZE = 11 ;

    private FieldCell[][] cells = new FieldCell[FIELD_SIZE][FIELD_SIZE];
    private ObservableList<FieldCell[]> data = FXCollections.observableArrayList(); ;

    public void init(){
        defaultFillArray();
    }

    public ImageView getImage(String letter , int number){
        return chooseCell(letter,number).getImageView();
    }
    public String getLetter(String letter , int number){
        return chooseCell(letter,number).getLetter();    }


    public FieldCell[][] getCells() {
        return cells;
    }



    private FieldCell chooseCell(String letter , int number){
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = cells[l][n];
                if(cell.getLetter().equals(letter) && cell.getNumber() == number){
                    return cell;
                }
            }
        }
        throw new IndexOutOfBoundsException("Outside BattleShip Field : " + letter + number);

    }

    private void defaultFillArray() {
        for(char c='А'; c <= 'К' ;c++ ){
            for(int i=1; i<FIELD_SIZE; i++){

                if(c=='Й') continue;
                int arrayNumber= getNumberFromChar(c);
                System.out.println("defaultFill : " + arrayNumber);
                String letter = String.valueOf(c);
                FieldCell cell = new FieldCell(letter , i);
                cells[i][arrayNumber] = cell;
            }
        }

        data.addAll(Arrays.asList(cells));
    }

    private int getNumberFromChar(char c){
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }
}
