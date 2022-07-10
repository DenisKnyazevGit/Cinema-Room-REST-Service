package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

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

