package home.battleShips.utils;

public class StaticUtils {


    public static int getNumberFromChar(char c){
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }

    public static int getNumberFromChar(String string){
        char c = string.charAt(0);
        if(c<'Й') return c-'А'+1;
        if(c>'Й') return c-'А';
        throw new IndexOutOfBoundsException("Letter is out of Battle Field : " + c );
    }
}
