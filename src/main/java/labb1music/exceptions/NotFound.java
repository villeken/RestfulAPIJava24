package labb1music.exceptions;

public class NotFound extends RuntimeException {
    public NotFound() {
        super();
    }
    public NotFound(String message) {
        super(message);
    }
}
