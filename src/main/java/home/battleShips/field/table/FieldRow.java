package home.battleShips.field.table;

import home.battleShips.field.FieldCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class FieldRow implements Comparable<FieldRow> {

    private Integer number;

//    private FieldCell a = new FieldCell("А");
//    private FieldCell b = new FieldCell("Б");
//    private FieldCell v = new FieldCell("В");
//    private FieldCell g = new FieldCell("Г");
//    private FieldCell d = new FieldCell("Д");
//    private FieldCell e = new FieldCell("Е");
//    private FieldCell zh = new FieldCell("Ж");
//    private FieldCell z = new FieldCell("З");
//    private FieldCell i = new FieldCell("И");
//    private FieldCell k = new FieldCell("К");

//    private FieldCell head;

//    private ObservableSet<FieldCell> cells = FXCollections.observableSet(a,b,v,g,d,e,zh,z,i,k);
    private ObservableSet<FieldCell> cells = FXCollections.observableSet();

    public FieldRow(){

    }

    public FieldRow(int number) {
        this.number = number;
//        defaultColumns();
    }



    @Override
    public int compareTo(FieldRow o) {

        return this.getNumber().compareTo( o.getNumber() );

    }


    public Integer getNumber() {
        return number;
    }

    public ObservableSet<FieldCell> getCells() {
        return cells;
    }

//    public FieldCell getA() {
//        return a;
//    }
//
//    public FieldCell getB() {
//        return b;
//    }
//
//    public FieldCell getV() {
//        return v;
//    }
//
//    public FieldCell getG() {
//        return g;
//    }
//
//    public FieldCell getD() {
//        return d;
//    }
//
//    public FieldCell getE() {
//        return e;
//    }
//
//    public FieldCell getZh() {
//        return zh;
//    }
//
//    public FieldCell getZ() {
//        return z;
//    }
//
//    public FieldCell getI() {
//        return i;
//    }
//
//    public FieldCell getK() {
//        return k;
//    }

//    private void defaultColumns(){
//        cells.add( new FieldCell(String.valueOf(number)) );
//        for(char c='А'; c <= 'К' ;c++ ){
//            if(c=='Й') continue;
//            cells.add( new FieldCell(String.valueOf(c)) );
//        }
//    }

}
