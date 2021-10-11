package home.BattleShips.Field.table;

import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;


//public class CellFactory implements Callback<TreeTableColumn.CellDataFeatures< FieldRow, ImageView> , ObservableValue<ImageView>> {
public class CellFactory  {

    private String letter;
    private FieldRow row ;

    private ObservableValue<ImageView> image ;

    public CellFactory(String letter , FieldRow row) {
        this.letter = letter;
        this.row = row;
    }

//    @Override
//    public ImageView call(FieldRow fieldRow) {
//        FieldCell result = new FieldCell() ;
//
//        for(FieldCell cell : fieldRow.getCells()){
//            System.out.println(cell.getLetter());
//            if( cell.getLetter().equals(letter) ){
//                return cell.getImageView();
//            }
//        }
//
//        return result.getImageView();
//    }

//    @Override
//    public ObservableValue<ImageView> call(TreeTableColumn.CellDataFeatures<FieldRow, ImageView> fieldRowImageViewCellDataFeatures) {
//        FieldCell result = new FieldCell() ;
//
//        for(FieldCell cell : row.getCells()){
//            System.out.println(cell.getLetter());
//            if( cell.getLetter().equals(letter) ){
//                return cell.getImageView();
//            }
//        }
//
//        return result.getImageView();
//    }
}
