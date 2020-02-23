package Project.demo;

import Project.controller.OrderController;

import java.util.Date;

public class DemoOrder {
    private static OrderController orderController = new OrderController();

    public static void main(String[] args){
        orderController.bookRoom(1001, 101, new Date(), new Date());

        orderController.cancelReservation(1001, 101);
    }
}