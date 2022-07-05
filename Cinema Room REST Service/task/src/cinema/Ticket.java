package cinema;

import java.util.UUID;

public class Ticket {
    // Токен билета
    private String token;
    // Купленное место
    private Seat seat;

    public Ticket(Seat seat) {
        this.seat = seat;
        this.token = UUID.randomUUID().toString();
    }

    public Ticket(String token) {
        this.seat = null;
        this.token = token;
    }

    public Ticket(Seat seat, String token) {
        this.seat = seat;
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public void regenerateUUID() {
        this.token = UUID.randomUUID().toString();
    }
}

