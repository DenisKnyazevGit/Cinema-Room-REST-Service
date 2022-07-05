package cinema;

public class CinemaCustomException extends RuntimeException {
    public CinemaCustomException(String cause) {
        super(cause);
    }
}