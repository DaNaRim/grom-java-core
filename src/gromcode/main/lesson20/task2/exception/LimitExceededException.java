package gromcode.main.lesson20.task2.exception;

public class LimitExceededException extends BadRequestException {

    public LimitExceededException(String message) {
        super(message);
    }
}
