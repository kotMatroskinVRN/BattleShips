package home.battleShips.model;

public class ShipCell {

    private final int letter;
    private final int number;

    public ShipCell(int letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    public int getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    public boolean isSamePlace(ShipCell cell){
        return letter == cell.getLetter() && number == cell.getNumber();
    }

    public boolean isNeighbour(ShipCell cell){
        return !isSamePlace(cell) &&
                Math.abs(letter - cell.getLetter() ) <= 1 &&
                Math.abs(number - cell.getNumber() ) <= 1;
    }
}
