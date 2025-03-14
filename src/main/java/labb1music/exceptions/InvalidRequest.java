package labb1music.exceptions;

public class InvalidRequest extends RuntimeException {
    public InvalidRequest() {
        super();
    }

    public InvalidRequest(String message) {
        super(message);
    }
}
