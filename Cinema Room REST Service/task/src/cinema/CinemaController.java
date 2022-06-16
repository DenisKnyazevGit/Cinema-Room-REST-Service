package cinema;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class CinemaController {
    private Cinema cinema;

    public CinemaController() {
        this.cinema = new Cinema(9, 9);
    }

    @GetMapping("/seats")
    public ResponseEntity<Cinema> getSeats() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Cinema> cinema = new ResponseEntity<>(this.cinema, headers, HttpStatus.OK);

        return cinema;
    }
}