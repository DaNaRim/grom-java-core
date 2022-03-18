package project.controller;

import project.exception.*;
import project.service.OrderService;

import java.util.Date;

public class OrderController {
    private static OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NoAccessException,
            InternalServerException,
            BadRequestException,
            NotLogInException,
            NotFoundException {
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NoAccessException,
            InternalServerException,
            BadRequestException,
            NotLogInException,
            NotFoundException {
        orderService.cancelReservation(roomId, userId);
    }
}