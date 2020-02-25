package Project.demo;

import Project.controller.OrderController;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;

import java.io.IOException;
import java.util.Date;

public class DemoOrder {
    private static OrderController orderController = new OrderController();

    public static void main(String[] args)
            throws NotLogInException, InternalServerException, NoAccessException, BadRequestException, IOException {
        orderController.bookRoom(1001, 101, new Date(), new Date());

        orderController.cancelReservation(1001, 101);
    }
}