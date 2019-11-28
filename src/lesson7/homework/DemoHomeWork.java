package lesson7.homework;

import java.util.Date;

public class DemoHomeWork {
    public static void main(String[] args) {

    }

    Order createOrder() {
        Order order1 = new Order();
        order1.price = 100;
        order1.dateCreated = new Date();
        order1.isConfirmed = false;
        order1.dateConfirmed = null;
        order1.city = "Dnepr";
        order1.country = "Ukraine";
        order1.type = "Buy";

            return order1;


    }

    Order createOrderAndCallMethods(){
        Order order2 = new Order();
        order2.price = 100;
        order2.dateCreated = new Date();
        order2.isConfirmed = true;
        order2.dateConfirmed = new Date();
        order2.city = "Kiev";
        order2.country = "Ukraine";
        order2.type = "SomeValue";

        order2.confirmOrder();
        order2.checkPrice();
        order2.isValidType();
        return order2;
    }


}
