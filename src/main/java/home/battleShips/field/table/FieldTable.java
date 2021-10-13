package home.battleShips.field.table;

import home.battleShips.field.FieldCell;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.Arrays;

public class FieldTable extends TableView<FieldCell[]> {
//public class FieldTable extends TableView<String[]> {

    //private ObservableList< FieldRow >               rows = FXCollections.observableArrayList();
    private ObservableList< TableColumn > tableColumnList = FXCollections.observableArrayList();
    private ObservableList<FieldCell[]>  data = FXCollections.observableArrayList(); ;
    private FieldCell[][] cells = new FieldCell[11][11];


    public FieldTable() {

        defaultFillArray();
        defaultColumns();


        //tableColumnList.get(0).setCellValueFactory(propertyValueFactory);
//        setItems(rows);

//        String[][] dataArray =
//                {{"First column", "Second column", "Third column", "Fourth column", "Fifth column"},
//                        {"1", "2", "3", "4", "5",},
//                        {"6", "7", "8", "9", "10",},
//                        {"11", "12", "13", "14", "15"}};
//        ObservableList<String[]> observableList = FXCollections.observableArrayList();
//        observableList.addAll(Arrays.asList(dataArray));
//        observableList.remove(0);
////        TableView<String[]> tableView = new TableView<>();
//
//        for (int i = 0; i < dataArray[0].length; i++) {
//            TableColumn tableColumn = new TableColumn(dataArray[0][i]);
//            int columnNumber = i;
//            tableColumn.setCellValueFactory(
//                    (Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>) p ->
//                            new SimpleStringProperty((p.getValue()[columnNumber])));
//            getColumns().add(tableColumn);
//        }
//        setItems(observableList);



        setItems(data);

    }

    private void defaultColumns(){
        createColumn();
        for(char c='А'; c <= 'К' ;c++ ){
            if(c=='Й') continue;
            createColumn(c);
        }
    }

    private void createColumn(){
        String letter = String.valueOf(' ');
        TableColumn<FieldCell[], Integer> tableColumn = new TableColumn(letter);
        PropertyValueFactory<FieldCell[], Integer> valueFactory = new PropertyValueFactory("number" );
        tableColumn.setPrefWidth(30);
        tableColumn.setCellValueFactory( valueFactory );

        tableColumnList.add( tableColumn );
        getColumns().add(tableColumn);

    }

    private void createColumn(char c) {
        String letter = String.valueOf(c);
        TableColumn<FieldCell[], ImageView> tableColumn = new TableColumn(letter);
        tableColumn.setPrefWidth(30);

        tableColumn.setCellValueFactory(
                p -> {
                    int columnNumber = getNumberFromChar(c);
                    System.out.println("cellValueFactory : " + columnNumber);
                    FieldCell cell = p.getValue()[columnNumber];
                    ImageView imageView = cell.getImageView();
                    System.out.println(imageView);
                    return new SimpleObjectProperty<ImageView>(imageView);
                }
        );

        //        PropertyValueFactory<FieldRow, ImageView> valueFactory = new CellFactory( letter );
//        Callback<FieldRow, ImageView> valueFactory = new CellFactory( letter );

//        tableColumn.setCellValueFactory( valueFactory );

//        int row =1;

//        tableColumn.setCellValueFactory(cd -> new SimpleObjectProperty<ImageView>( rows.get(row).getCells() ));

//        tableColumn.setCellFactory((column) ->
//            new TableCell<FieldRow, ImageView>() {
//                @Override
//                protected void updateItem(ImageView item, boolean empty) {
//
//                    String letter = column.getText();
//                    for( FieldCell cell : rows.get(row).getCells() ){
//                        System.out.println(cell.getImageView());
//                        if( cell.getLetter().equals(letter) ){
//                            super.updateItem(cell.getImageView(), empty);
//                        }
//                    }
//
//                    super.updateItem( (new FieldCell()).getImageView(), empty);
//                }
//            }
//        );



        tableColumnList.add( tableColumn );
        getColumns().add(tableColumn);
    }

    private void defaultFillArray() {
        for(char c='А'; c <= 'К' ;c++ ){
            for(int i=1; i<=10; i++){

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
        return 55;
    }


}
