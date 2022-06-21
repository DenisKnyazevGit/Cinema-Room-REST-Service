package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

class SeatPurchaseException extends RuntimeException {
    public SeatPurchaseException(String cause) {
        super(cause);
    }
}

public class Cinema {
    // Количество рядов в кинотеатре
    private int totalRows = 0;

    // Количество мест в ряду
    private int totalColumns = 0;

    // Массив мест в кинотеатре
    private ArrayList<Seat> allSeats;

    public Cinema() {
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.allSeats = new ArrayList<Seat>();

        for (int i = 0; i < this.totalRows; i++) {
            for (int j = 0; j < this.totalColumns; j++) {
                int price = i + 1 < 5 ? 10 : 8;
                this.allSeats.add(new Seat(i + 1, j + 1, price));
            }
        }
    }

    @JsonProperty("available_seats")
    public ArrayList<Seat> getAvailableSeats() {
        ArrayList<Seat> availableSeats = new ArrayList<Seat>();
        for (Seat s : this.allSeats) {
            if (s != null) {
                availableSeats.add(s);
            }
        }
        return availableSeats;
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return this.totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return this.totalColumns;
    }

    public Seat purchaseSeat(int row, int column) {
        if (row > this.totalRows || row < 0 || column > this.totalColumns || column < 0) {
            throw new SeatPurchaseException("The number of a row or a column is out of bounds!");
        }

        int seatNumber = (row - 1) * this.totalRows + (column - 1);
        Seat s = this.allSeats.get(seatNumber);

        if (s == null) {
            throw new SeatPurchaseException("The ticket has been already purchased!");
        }

        this.allSeats.set( seatNumber, null );

        return s;
    }
}