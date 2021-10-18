package home.battleShips.field;

import home.battleShips.utils.StaticUtils;
import javafx.scene.image.ImageView;

public class FieldData {

    private final int FIELD_SIZE = 11 ;

    private final FieldCell[][] cells = new FieldCell[FIELD_SIZE][FIELD_SIZE];

    public void init(){

        long start;
        double time;

        System.out.println("\ndefault fill array");
        start = System.currentTimeMillis();
        defaultFillArray();
        time  = (System.currentTimeMillis() - start)/1000.0 ;
        System.out.printf( "run time : %f seconds\n" , time );

    }

    public ImageView getImage(String letter , int number){
        return chooseCell(letter,number).getImageView();
    }
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
                int arrayNumber= StaticUtils.getNumberFromChar(c);
                //System.out.println("defaultFill : " + arrayNumber);
                String letter = String.valueOf(c);
                FieldCell cell = new FieldCell(letter , i);
                cells[arrayNumber][i] = cell;

            }
        }
    }

}
