package home.BattleShips.model;

import home.BattleShips.Field.FieldCell;

public class Game {


    static final int FS        = 12 ;
    static int count_kills     = 0;
    static int count_kills_cpu = 0;
    private Ship[] shipsCPU      ;
    private Ship[] shipsPLAYER   ;



    public Game() {

        shipsCPU    = randomSetOfShips() ;
        shipsPLAYER = randomSetOfShips() ;



       // block  = createPlayground( 0   , 0 , true ) ;
      //  rblock = createPlayground( 400 , 0 , false ) ;


//        for( Ship elm: shipsPLAYER ){
//            for(int y=1;y<FS-1;y++){ for(int x=1;x<FS-1;x++){
//                if( elm.footprint[x][y] == true )	rblock[x][y].setIcon(deck) ;
//            }}// for x , y
//
//        }

		/*for( ship elm: shipsCPU ){
				for(int y=1;y<FS-1;y++){ for(int x=1;x<FS-1;x++){
					if( elm.footprint[x][y] == true )	block[x][y].setIcon(deck) ;
				}}// for x , y

			}*/


    }// Main constructor


   // JButton[][] createPlayground( int xi , int yi , boolean cpu ){
      //  JButton[][] res = new JButton[FS][FS] ;

       // JLabel[]	      a2j = new JLabel[FS] ;
       // JLabel[]  	one2ten = new JLabel[FS] ;
     //   char tmp;

     //   for(int y=1;y<FS-1;y++){ for(int x=1;x<FS-1;x++){



            //res[x][y] = new JButton(sea);
            //res[x][y].setBounds( xi+pitch+x*cellSize , yi+pitch+y*cellSize , cellSize , cellSize );
            //res[x][y].setFocusPainted(true);
            //res[x][y].setOpaque(true);

//            if( cpu ) {
//                res[x][y].addActionListener(new ActionListener() {  // in case of disable try - res[x][y].removeActionListener
//                    public void actionPerformed(ActionEvent e) {
//                        JButton pressedButton = (JButton) e.getSource();
//
//                        int[] tmp = new int[2];
//                        tmp = action(pressedButton); counter_action(tmp[0] , tmp[1]);
//                    }
//                });
//
//            } // if cpu
//            else{ res[x][y].setFocusPainted(false);
//                //res[x][y].setEnabled(false);
//                res[x][y].setSelected(false) ;
//                // System.out.println("enter the ELSE");
//            }
//
//
//            frm.add(res[x][y]); ///  FRM  ///


      //  }} // for x , y


    //    return res ;
   // }


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

                for( Ship elm: shipsCPU ){


                    if( elm.getFootprint()[x][y] == true )	{


                        cell.setImageHit();

                        elm.hits[x][y] = true ;
                        if( elm.countHits()  ){	/// bug - if click on killed -> count++ ; add check if alive
                            //surroundShip(elm,block);

                            if( elm.alive ) {	count_kills++;	elm.alive = false ; }
                            System.out.println(count_kills);
                            if( count_kills == 10 ){

//                                for(int y1=1;y1<FS-1;y1++){ for(int x1=1;x1<FS-1;x1++){
//                                    block[x1][y1].setEnabled(false);  /// BLOCK ///
//                                    rblock[x1][y1].setEnabled(false); }} /// RBLOCK ///

//                                JFrame You_Win = new JFrame("You Win!!!") ;
//                                You_Win.setBounds( 50 , 50 , 450 , 338 );
//
//                                You_Win.setLayout(null);
//                                JLabel victory = new JLabel(vict , JLabel.CENTER) ;
//                                victory.setBounds( 0 , 0, 450 , 338 );
//                                You_Win.add(victory);
//                                You_Win.setVisible(true);
                                // make inactive after closing congratulation vindow


                                //JLabel victory = new JLabel( vict , JLabel.CENTER ) ;
                                //victory.setBounds( 10 , 10, 450 , 338 );
                                //victory.setOpaque(false);
                                //frm.add(victory);

                                //frm.setVisible(true);
                                //frm.repaint();
                                //System.out.println("enter the MATRIX");
                            }// if victory
                        }// if kill

                    }//if footprint

                }//foreach

                if( cell.getImageView() == cell.SEA  ) {
                    cell.setImageMiss();
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
