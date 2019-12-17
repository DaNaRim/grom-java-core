package lesson10.Homework;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Ira", "Одесса", "Женский");
        Customer customer2 = new Customer("Oleg", "Львов", "Мужской");

        ElectronicsOrder electronicsOrder1 = new ElectronicsOrder(
                "Електрокактус", new Date(), "Киев",
                "Днепр", 90, customer1, 2);
        ElectronicsOrder electronicsOrder2 = new ElectronicsOrder(
                "Чайник", new Date(), "Одесса",
                "Харьков", 500, customer2, 12);

        FurnitureOrder furnitureOrder1 = new FurnitureOrder(
                "Книжная полка", new Date(), "Киев",
                "Одесса", 600, customer1, "31312348");
        FurnitureOrder furnitureOrder2 = new FurnitureOrder(
                "ДВС", new Date(), "Харьков",
                "Шкаф", 4000, customer2, "88000555");

        electronicsOrder1.validateOrder();
        electronicsOrder1.calculatePrice();

        electronicsOrder2.validateOrder();
        electronicsOrder2.calculatePrice();

        furnitureOrder1.validateOrder();
        furnitureOrder1.calculatePrice();

        furnitureOrder2.validateOrder();
        furnitureOrder2.calculatePrice();

    }
}
