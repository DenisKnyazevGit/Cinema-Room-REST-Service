package cinema;

public class Cinema {
    // Количество рядов в кинотеатре
    private int totalRows = 0;

    // Количество мест в ряду
    private int totalColumns = 0;

    // Массив мест в кинотеатре
    private Seat[] availableSeats;

    public Cinema() {
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new Seat[totalRows * totalColumns];

        for (int i = 0; i < this.totalRows; i++) {
            for (int j = 0; j < this.totalColumns; j++) {
                int availableSeatNumber = i * this.totalRows + j;
                this.availableSeats[availableSeatNumber] = new Seat(i + 1, j + 1);
            }
        }
    }

    public Seat[] getavailable_seats() {
        return this.availableSeats;
    }

    public int gettotal_rows() {
        return this.totalRows;
    }

    public int gettotal_columns() {
        return this.totalColumns;
    }
}