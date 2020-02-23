package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NotRegisteredException;
import Project.service.OrderService;

import java.io.IOException;
import java.util.Date;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        try {
            orderService.bookRoom(roomId, userId, dateFrom, dateTo);
            System.out.println("bookRoom successful: " + roomId);
        } catch (NotRegisteredException | IOException | InternalServerException | BadRequestException e) {
            System.out.println("bookRoom failed: " + e.getMessage());
        }
    }

    public void cancelReservation(long roomId, long userId) {
        try {
            orderService.cancelReservation(roomId, userId);
            System.out.println("cancelReservation successful");
        } catch (NotRegisteredException | IOException | InternalServerException | BadRequestException e) {
            System.err.println("cancelReservation failed: " + e.getMessage());
        }
    }
}