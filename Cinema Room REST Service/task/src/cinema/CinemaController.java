package cinema;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class CinemaController {
    private Cinema cinema;

    public CinemaController() {
        this.cinema = new Cinema(9, 9);
    }

    @GetMapping("/seats")
    public ResponseEntity getSeats() {
        ResponseEntity cinema = new ResponseEntity<>(this.cinema, null, HttpStatus.OK);

        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity postPurchase(@RequestBody Seat seat) {
        ResponseEntity responseEntity;
        try {
            PurchaseResponse r = this.cinema.purchaseTicket(seat.getRow(), seat.getColumn());
            responseEntity =  new ResponseEntity<>(r, null, HttpStatus.OK);
        } catch (CinemaCustomException e) {
            responseEntity = new ResponseEntity<>(new CinemaCustomError(e.getMessage()), null, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PostMapping("/return")
    public ResponseEntity postReturn(@RequestBody Ticket ticket) {
        ResponseEntity responseEntity;
        try {
            ReturnResponse r = this.cinema.returnTicket(ticket.getToken());
            responseEntity =  new ResponseEntity<>(r, null, HttpStatus.OK);
        } catch (CinemaCustomException e) {
            responseEntity = new ResponseEntity<>(new CinemaCustomError(e.getMessage()), null, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}