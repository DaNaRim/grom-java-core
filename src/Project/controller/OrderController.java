package Project.controller;

import Project.exception.*;
import Project.service.OrderService;

import java.io.IOException;
import java.util.Date;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws NotLogInException, InternalServerException, NoAccessException, BadRequestException, IOException {
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId)
            throws NotLogInException, InternalServerException, NoAccessException, BadRequestException, IOException {
        orderService.cancelReservation(roomId, userId);
    }
}