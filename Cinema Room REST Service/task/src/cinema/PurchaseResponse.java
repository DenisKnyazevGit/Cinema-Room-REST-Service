package cinema;

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

