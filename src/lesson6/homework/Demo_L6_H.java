package lesson6.homework;

import java.util.Date;

public class Demo_L6_H {
    public static void main(String[] args) {
        Order order1 = new Order(1200, new Date(), true, new Date(), "TestCity", "TestCountry", "Buy");
        Order order2 = new Order(900, new Date(), true, new Date(), "TestCity", "TestCountry", "Sale");
        Order order3 = new Order(1000, new Date(), true, new Date(), "TestCity", "TestCountry", "TestType");


        //test checkPrice Order class

        //price > 1000
        //price < 1000
        //price = 1000

        System.out.println(order1.checkPrice());
        System.out.println(order2.checkPrice());
        System.out.println(order3.checkPrice());
        System.out.println();

        //test isValidType Order class

        //type == "Buy"
        //type == "Sale"
        //type != "Buy" && != "Sale"

        System.out.println(order1.isValidType());
        System.out.println(order2.isValidType());
        System.out.println(order3.isValidType());
        System.out.println();
    }
}
