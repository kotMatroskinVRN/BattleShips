package home.BattleShips.Field.table;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class CellFactoryNumber<FieldRow, String> extends PropertyValueFactory<FieldRow, String> {

    String letter;
    FieldRow fieldRow;

    public CellFactoryNumber(String letter) {
        super(java.lang.String.valueOf(letter));
        this.letter = letter;
    }

//    @Override
//    public ObservableValue<String> call(FieldRow row) {
////        FieldCell result = new FieldCell() ;
//
//        for(FieldCell cell : row.){
//            System.out.println(cell.getLetter());
//            if( cell.getLetter().equals(letter) ){
//                return cell.getLetter();
//            }
//        }
//
//        return result.getLetter();
//    }


}
