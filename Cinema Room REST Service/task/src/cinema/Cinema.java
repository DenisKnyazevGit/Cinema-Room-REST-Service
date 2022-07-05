package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

class PurchaseResponse {
    // Токен билета
    private String token;
    // Купленное место
    private Seat ticket;

    public PurchaseResponse(Ticket ticket) {
        this.ticket = ticket.getSeat();
        this.token = ticket.getToken();
    }

    public String getToken() {
        return this.token;
    }

    public Seat getTicket() {
        return this.ticket;
    }
}

class ReturnResponse {
    // Купленное место
    private Seat returnedTicket;

    public ReturnResponse(Ticket ticket) {
        this.returnedTicket = ticket.getSeat();
    }

    @JsonProperty("returned_ticket")
    public Seat getReturnedTicket() {
        return this.returnedTicket;
    }
}

public class Cinema {
    // Количество рядов в кинотеатре
    private int totalRows = 0;

    // Количество мест в ряду
    private int totalColumns = 0;

    // Массив мест в кинотеатре
    private ArrayList<Seat> allSeats;

    // Массив купленных билетов
    private ConcurrentHashMap<String, Ticket> allTickets;

    public Cinema() {
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.allSeats = new ArrayList<Seat>();
        this.allTickets = new ConcurrentHashMap<>();

        for (int i = 0; i < this.totalRows; i++) {
            for (int j = 0; j < this.totalColumns; j++) {
                int price = i + 1 < 5 ? 10 : 8;
                Seat s = new Seat(i + 1, j + 1, price);
                this.allSeats.add(s);
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

    public PurchaseResponse purchaseTicket(int row, int column) {
        if (row > this.totalRows || row < 0 || column > this.totalColumns || column < 0) {
            throw new CinemaCustomException("The number of a row or a column is out of bounds!");
        }

        int seatNumber = (row - 1) * this.totalRows + (column - 1);
        Seat s = this.allSeats.get(seatNumber);

        if (s == null) {
            throw new CinemaCustomException("The ticket has been already purchased!");
        }

        Ticket t = new Ticket(s);
        while (this.allTickets.get(t.getToken()) != null) {
            t.regenerateUUID();
        }

        this.allTickets.put(t.getToken(), t);

        this.allSeats.set(seatNumber, null);

        return new PurchaseResponse(t);
    }

    public ReturnResponse returnTicket(String token) {
        Ticket returnedTicket = this.allTickets.get(token);
        if (returnedTicket == null) {
            throw new CinemaCustomException("Wrong token!");
        }

        Seat s = returnedTicket.getSeat();
        int seatNumber = (s.getRow() - 1) * this.totalRows + (s.getColumn() - 1);
        this.allSeats.set(seatNumber, s);
        this.allTickets.remove(returnedTicket.getToken());

        return new ReturnResponse(returnedTicket);
    }
}