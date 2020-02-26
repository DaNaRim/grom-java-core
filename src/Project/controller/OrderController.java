package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NotLogInException;
import Project.service.OrderService;

import java.util.Date;

public class OrderController {
    private static OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NotLogInException, InternalServerException, BadRequestException {
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NotLogInException, InternalServerException, BadRequestException {
        orderService.cancelReservation(roomId, userId);
    }
}