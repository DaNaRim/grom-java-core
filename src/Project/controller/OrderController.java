package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.service.OrderService;

import java.util.Date;

public class OrderController {
    private static OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NoAccessException, InternalServerException, BadRequestException {
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NoAccessException, InternalServerException, BadRequestException {
        orderService.cancelReservation(roomId, userId);
    }
}