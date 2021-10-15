package home.battleShips.model;

import home.battleShips.field.FielPicture;
import home.battleShips.field.FieldCell;

import java.util.Date;

public class Game {


    static final int FS        = 12 ;
    static int count_kills     = 0;
    static int count_kills_cpu = 0;
    private Ship[] shipsCPU      ;
    private Ship[] shipsPLAYER   ;



    public Game() {
        shipsCPU    = randomSetOfShips() ;
        shipsPLAYER = randomSetOfShips() ;

        System.out.println(new Date());

    }



    public Ship[] getShipsCPU() {
        return shipsCPU;
    }

    public Ship[] getShipsPLAYER() {
        return shipsPLAYER;
    }

    public void action(FieldCell cell){ // uses global - block (.. , boolean[] iblock , boolean[] irblock????)

        System.out.println("Game action");

        int y = cell.getNumber();
        int x = getNumberFromChar( cell.getLetter() );

            for( Ship ship: shipsCPU ){
                if( ship.hasCell(x,y) )	{
                    cell.setImage(FielPicture.HIT);
                    ship.addHit(x ,y)  ;

                    if( ship.isKilled()  ){
                        //surroundShip(elm,block);

                        System.out.println(count_kills);
                        if( count_kills == 10 ){

                        }// if victory
                    }// if kill
                    else{
                        count_kills++;
                    }
                }//if footprint

            }//foreach

            if( cell.getImageView().getImage() == FielPicture.SEA.getImage()  ) {
                cell.setImage(FielPicture.MISS);
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


    private Ship[] randomSetOfShips() {
        long start = System.currentTimeMillis();

        int size , n , l ;
        char  dc;
        char[] d = { 'h' , 'v' };
        Ship[] rShips = new Ship[FS-2] ;

        for(int i = 0 ; i<FS-2 ; i++ ){
            size = (int)( 12 - 1.5*i )/4 + 1 ;
            l  = (int)( Math.random()*(FS-1-size) ) +1  ;
            n  = (int)( Math.random()*(FS-1-size) ) +1  ; // fs-1????
            dc = d[(int)( Math.random()*2)] ;

            rShips[i] = new Ship( size , l  , n , dc );


            while( i>0 && !( checkShipsArray( rShips , i ) ) ){
                l  = (int)( Math.random()*(FS-1-size) ) +1  ;
                n  = (int)( Math.random()*(FS-1-size) ) +1  ;
                dc = d[(int)( Math.random()*2)] ;
                rShips[i] = new Ship( size , l  , n , dc );
            }
            //System.out.printf("%d is O.K. \n" , size );
        }// for ships array



        return rShips ;
    }//randomSetOfShips

    private boolean checkShipsArray(Ship[] pa , int size){
        boolean flag = true ;

        for( int i=0;i<=size;i++ ){ for( int j=0;j<=size;j++ ){
            if( i!=j && !( pa[i].check2Ships(pa[j]) ) ){ flag = false; }
        }}

        return flag ;
    }//checkShipsArray

    private int getNumberFromChar(String string){
        char c = string.charAt(0);
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }

}
