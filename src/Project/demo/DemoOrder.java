package Project.demo;

import Project.controller.OrderController;
import Project.controller.UserController;
import Project.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DemoOrder {
    private static OrderController orderController = new OrderController();
    private static UserController userController = new UserController();

    public static void main(String[] args)
            throws NoAccessException, InternalServerException, BadRequestException, ParseException {

        userController.login("Oleg", "wf45a4w44f");

        userController.login("DaNaRim", "f5urhg%89aohfol347hgfv93");

        orderController.bookRoom(5946096940029333433L, 4209196781363284626L,
                new SimpleDateFormat("dd.MM.yyyy kk:00").parse("26.04.2020 12:00"),
                new SimpleDateFormat("dd.MM.yyyy kk:00").parse("27.04.2020 12:00"));

        userController.logout();

        userController.login("Oleg2", "wf47777w44f");

        orderController.bookRoom(5946096940029333433L, 3916769799713539086L,
                new SimpleDateFormat("dd.MM.yyyy").parse("26.04.2020"),
                new SimpleDateFormat("dd.MM.yyyy").parse("28.04.2020"));

        orderController.cancelReservation(5946096940029333433L, 4209196781363284626L);
    }
}