package cinema;

public class Seat {
    // Номер ряда
    private int row = 0;
    // Номер места
    private int column = 0;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}