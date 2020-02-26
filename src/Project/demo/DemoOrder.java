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
            throws NotLogInException, InternalServerException, BadRequestException, ParseException {

        userController.login("Oleg", "wf45a4w44f");

        userController.login("DaNaRim", "f5urhg%89aohfol347hgfv93");

        orderController.bookRoom(5946096940029333433L, 6025197426307648867L,
                new SimpleDateFormat("dd.MM.yyyy kk:00").parse("26.02.2020 12:00"),
                new SimpleDateFormat("dd.MM.yyyy kk:00").parse("27.02.2020 12:00"));

        orderController.bookRoom(5946096940029333433L, 7734607807666275015L,
                new SimpleDateFormat("dd.MM.yyyy").parse("26.02.2020"),
                new SimpleDateFormat("dd.MM.yyyy").parse("28.02.2020"));

        orderController.cancelReservation(5946096940029333433L, 6025197426307648867L);
    }
}