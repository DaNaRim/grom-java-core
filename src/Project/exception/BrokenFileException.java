package Project.exception;

public class BrokenFileException extends InternalServerException {

    public BrokenFileException(String message) {
        super(message);
    }
}