package gromcode.main.lesson20.task1.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}