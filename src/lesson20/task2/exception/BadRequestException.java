package lesson20.task2.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String message) {
        super(message);
    }
}
