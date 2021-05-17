package nl.thomasvanminnen.rest.dtos;

public class RestErrorDTO {
    private final String errorMessage;
    private final int errorCode;

    public RestErrorDTO(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
