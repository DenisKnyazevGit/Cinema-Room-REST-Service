package cinema;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

class ErrorBody {
    private String error;

    public ErrorBody(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}

@RestController
public class CinemaController {
    private Cinema cinema;

    public CinemaController() {
        this.cinema = new Cinema(9, 9);
    }

    @GetMapping("/seats")
    public ResponseEntity getSeats() {
        // HttpHeaders headers = new HttpHeaders();
        ResponseEntity cinema = new ResponseEntity<>(this.cinema, null, HttpStatus.OK);

        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity postPurchase(@RequestBody Seat seat) {
        ResponseEntity responseEntity;
        try {
            Seat s = this.cinema.purchaseSeat(seat.getRow(), seat.getColumn());
            responseEntity =  new ResponseEntity<>(s, null, HttpStatus.OK);
        } catch (SeatPurchaseException e) {
            responseEntity = new ResponseEntity<>(new ErrorBody(e.getMessage()), null, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}