package project.controller;

import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.exception.NoAccessException;
import project.exception.NotLogInException;
import project.service.OrderService;

import java.util.Date;

public class OrderController {
    private static OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NoAccessException, InternalServerException, BadRequestException, NotLogInException {
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NoAccessException, InternalServerException, BadRequestException, NotLogInException {
        orderService.cancelReservation(roomId, userId);
    }
}