package home.BattleShips.model;

public class Ship{


    static final int   FS = Game.FS ;


    private int size ;
    private char dir;
    private boolean[][] footprint = new boolean[FS][FS];
    boolean[][]      hits = new boolean[FS][FS];
    boolean 		alive  ;

    Ship( int s , int li , int ni , char d ){

        if(s<1){ System.out.printf("Ship %2d %c%d position is out of range of size \n" , s , li , ni );	 }

        // split to 2 sections : start position and end position
        if (d == 'v') {
            for (int i = 0; i < s; i++) {
                footprint[li][ni + i] = true;
            }
        } else {
            for (int i = 0; i < s; i++) {
                footprint[li + i][ni] = true;
            }
        }

        size = s; dir = d ; alive = true ;
    }

    public boolean[][] getFootprint() {
        return footprint;
    }

    // check positions of THIS ship and given one
    boolean check2Ships(Ship p ){
        boolean flag = true ;
        for(int n=1;n<FS-1;n++){for(int l=1;l<FS-1;l++){

            if( this.footprint[l][n] && p.footprint[l][n] ){ flag = false ; }

            for(int dl=-1;dl<=1;dl++){	for(int dn=-1;dn<=1;dn++){
                if( dn!=0 || dl!=0 ) {
                    if( footprint[l][n] && p.footprint[l+dl][n+dn] ){
                        flag = false ;
                    }}
            }}// for surround : dl and dn

        }}// for entire field
        return flag ;
    }//chsckShips




    boolean countHits(){
        int count = 0; boolean flag = false ;

        for(int n=1;n<FS-1;n++){for(int l=1;l<FS-1;l++){if( this.hits[l][n] ) { count++; } }}// for entire field
        if( count == this.size){ flag = true ;  }

        return flag ;

    }




}//class ship

