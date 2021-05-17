package nl.thomasvanminnen.rest.exceptions;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String message) {
        super(message);
    }
}
