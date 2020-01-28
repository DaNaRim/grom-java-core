package lesson20.task2.exception;

public class LimitExceeded extends BadRequestException{

    public LimitExceeded(String message) {
        super(message);
    }
}
