package home.battleShips.model;

import home.battleShips.Controller;
import home.battleShips.field.FieldPicture;
import home.battleShips.field.FieldCell;
import home.battleShips.field.grid.FieldGrid;
import home.battleShips.utils.StaticUtils;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.util.Date;
import java.util.Stack;

public class Game {

    private final int FIELD_SIZE = 11 ;
    static final int FS        = 12 ;
    private final Ship[] shipsCPU      ;
    private final Ship[] shipsPLAYER   ;
    private final Stack<Turn> playersTurns;
    private final Stack<Turn> cpusTurns;


    private final FieldGrid playerField;
    private final FieldGrid cpuField;
    private final  Controller controller;

    public Game(Controller controller) {
        this.controller = controller;

        playersTurns = new Stack<>();
        cpusTurns    = new Stack<>();

        playerField = new FieldGrid();
        cpuField    = new FieldGrid();

        playerField.init();
        cpuField.init();

        shipsCPU    = Ship.randomSetOfShips() ;
        shipsPLAYER = Ship.randomSetOfShips() ;

        showPlayersShips();

        System.out.println(new Date());

        setListeners();

    }


    public FieldGrid getPlayerField() {
        return playerField;
    }

    public FieldGrid getCpuField() {
        return cpuField;
    }

    public Ship[] getShipsCPU() {
        return shipsCPU;
    }

    public Ship[] getShipsPLAYER() {
        return shipsPLAYER;
    }


    private void turn( FieldCell cell) {



        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println(cell.getLetter()+number);


        Turn turn = new Turn(cell);

        //boolean hit = false;
        for(Ship ship : shipsPLAYER){
            if( ship.hasCell(letter,number )){
                turn.setStatus(TurnStatus.HIT);
                ship.addHit(letter,number);
                playerField.setImageToGridCell( cell, FieldPicture.HIT);
                if(ship.isKilled()){
                    turn.setStatus(TurnStatus.KILL);
                    killShip(ship,playerField);
                }
                break;
            }
        }


        if(turn.getStatus()==TurnStatus.MISS){
            playerField.setImageToGridCell( cell, FieldPicture.MISS);
            counterAction();
        }




    }

    private void counterAction() {
        Turn turn = new Turn(cpuField);

        FieldCell cell = turn.getCell();
        int letter = StaticUtils.getNumberFromChar(cell.getLetter());
        int number = cell.getNumber();
        System.out.println("cpu:" + cell.getLetter()+number);

        for(Ship ship : shipsCPU){
            if( ship.hasCell(letter,number )){
                turn.setStatus(TurnStatus.HIT);
                ship.addHit(letter,number);
                cpuField.setImageToGridCell( cell, FieldPicture.HIT);
                if(ship.isKilled()){
                    turn.setStatus(TurnStatus.KILL);
                    killShip(ship , cpuField);
                }
                break;
            }
        }

        if(turn.getStatus()==TurnStatus.MISS){
            cpuField.setImageToGridCell( cell, FieldPicture.MISS);

        }else{
            counterAction();
        }

    }


//    void counter_action(int xi , int yi){ // uses global - block ; do action when clicked to already shooted block(
//
//
//        int x  , y  ;
//        if( !(y_shot[xi][yi]) && block[xi][yi].getIcon() != hit  ){
//            if( xi != 1 || yi != 1 )y_shot[xi][yi] = true ;
//
//            System.out.println("counter_action");
//
//            x = (int)( Math.random()*(FS-2) ) +1  ;
//            y = (int)( Math.random()*(FS-2) ) +1  ;
//
//            while( c_shot[x][y] || x==0 ||  x>10 || y==0 || y>10 ){
//                x = (int)( Math.random()*(FS-2) ) +1  ;
//                y = (int)( Math.random()*(FS-2) ) +1  ;
//            }
//
//            c_shot[x][y] = true ;
//
//
//            for( Ship elm: shipsPLAYER ){
//                if( elm.footprint[x][y] == true )	{
//
//                    rblock[x][y].setIcon(hit) ;
//
//                    elm.hits[x][y] = true ;
//                    if( elm.countHits()  ){	/// bug - if click on killed -> count++ ; add check if alive
//                        surroundShip(elm,rblock);
//
//                        if( elm.alive ) {	count_kills_cpu++;	elm.alive = false ; }
//
//                        if( count_kills_cpu == 10 ){
//
//                            for(int y1=1;y1<FS-1;y1++){ for(int x1=1;x1<FS-1;x1++){
//                                block[x1][y1].setEnabled(false);
//                                rblock[x1][y1].setEnabled(false); }}
//
//                            JFrame You_loose = new JFrame("Game Over") ;
//                            You_loose.setBounds( 50 , 50 , 848 , 480 );
//
//                            You_loose.setLayout(null);
//                            JLabel game_over = new JLabel(loss , JLabel.CENTER) ;
//                            game_over.setBounds( 0 , 0, 848 , 480 );
//                            You_loose.add(game_over);
//                            You_loose.setVisible(true);
//                        }// if victory
//                    }// if kill
//
//                }//if footprint
//
//            }//foreach
//
//            if( rblock[x][y].getIcon() == sea  ) rblock[x][y].setIcon(miss) ;
//
//            if( rblock[x][y].getIcon() == hit  ) counter_action(1,1) ;
//        } // if did not shoot on cpu board
//
//    }

//    void surroundShip (Ship s , JButton[][] iBlock ){
//        for(int y=1;y<FS-1;y++){ for(int x=1;x<FS-1;x++){
//            if( s.footprint[x][y] ) {
//                for(int dl=-1;dl<=1;dl++){	for(int dn=-1;dn<=1;dn++){
//                    if( !(s.footprint[x+dl][y+dn]) && x+dl>0 && x+dl<11 && y+dn>0 && y+dn<11 ) {
//                        iBlock[x+dl][y+dn].setIcon(miss) ;
//                        y_shot[x+dl][y+dn] = true ;//set shoot
//                    }
//                }}// for surround : dl and dn
//            }// if footprint
//
//        }}// for entire field
//    }//surround ship


    private void setListeners() {
        for(int l=1;l<FIELD_SIZE;l++){
            for(int n=1;n<FIELD_SIZE;n++){
                FieldCell cell = playerField.getFieldData().getCells()[l][n];
                cell.getImageView().onMouseClickedProperty().set( ae -> turn( cell ) );

            }
        }
    }

    private void showPlayersShips() {
        for(Ship ship : getShipsCPU()){

            for(ShipCell shipCell : ship.getShipCellList()){
                int l = shipCell.getLetter();
                int n = shipCell.getNumber();
                FieldCell cell = cpuField.getFieldData().getCells()[l][n];
                cell.setImage(FieldPicture.DECK);
                GridPane.setConstraints(cell.getImageView(), l, n);
                cpuField.getChildren().add(cell.getImageView());
            }
        }
    }





    private void killShip(Ship ship , FieldGrid fieldGrid) {
        ship.surroundShip(fieldGrid);
        fieldGrid.addKill();

        if(fieldGrid.getCount_kills()==1){
            System.out.println(fieldGrid + "victory");
            controller.showVictory();

        }

    }

}
