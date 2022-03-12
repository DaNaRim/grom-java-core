package main.lesson22.homework.exception;

public class LimitExceeded extends BadRequestException {

    public LimitExceeded(String message) {
        super(message);
    }
}
