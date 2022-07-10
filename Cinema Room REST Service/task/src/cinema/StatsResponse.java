package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

class StatsResponse {
    // Сумма купленных билетов
    private int currentIncome = 0;

    //Количество свободных мест
    private int numberOfAvailableSeats;

    //Количество проданных билетов
    private int numberOfPurchasedTickets;

    public StatsResponse(ArrayList<Seat> availableSeats, ConcurrentHashMap<String, Ticket> allTickets) {
        this.numberOfAvailableSeats = availableSeats.size();
        this.numberOfPurchasedTickets = allTickets.size();
        for (Ticket t : allTickets.values()) {
            this.currentIncome += t.getSeat().getPrice();
        }
    }

    @JsonProperty("current_income")
    public int getCurrentIncome() {
        return this.currentIncome;
    }

    @JsonProperty("number_of_available_seats")
    public int getNumberOfAvailableSeats() {
        return this.numberOfAvailableSeats;
    }

    @JsonProperty("number_of_purchased_tickets")
    public int getNumberOfPurchasedTickets() {
        return this.numberOfPurchasedTickets;
    }
}

