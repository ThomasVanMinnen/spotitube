package nl.thomasvanminnen.rest.exceptions;

public class IncorrectUserDetailsException extends RuntimeException {
    public IncorrectUserDetailsException(String message) {
        super(message);
    }
}
