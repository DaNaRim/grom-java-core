package gromcode.main.lesson10.Homework;

import java.util.Date;

public class Demo_L10_H {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Ira", "Одесса", "Женский");
        Customer customer2 = new Customer("Тест", "Львов", "Мужской");

        //test "validateOrder" FurnitureOrder class

        //валидация
        //когда не подходящий город c которого заказивают
        //когда минимальная цена заказа < 500
        //когда имя заказчика "Тест"

        FurnitureOrder furnitureOrder1 = new FurnitureOrder("Книжная полка", new Date(), "Киев", "Одесса", 6000, customer1, "31312348");
        furnitureOrder1.validateOrder();

        furnitureOrder1 = new FurnitureOrder("Шкаф", new Date(), "Харьков", "Харьков", 5000, customer1, "88000555");
        furnitureOrder1.validateOrder();

        furnitureOrder1 = new FurnitureOrder("Шкаф", new Date(), "Киев", "Харьков", 400, customer1, "88000555");
        furnitureOrder1.validateOrder();

        furnitureOrder1 = new FurnitureOrder("Шкаф", new Date(), "Киев", "Харьков", 5000, customer2, "88000555");
        furnitureOrder1.validateOrder();

        System.out.println();

        //test "calculatePrice" FurnitureOrder class

        //расчет цены если сумма < 5000
        //расчет цены если сумма = 5000
        //расчет цены если сумма > 5000

        FurnitureOrder furnitureOrder2 = new FurnitureOrder("Книжная полка", new Date(), "Киев", "Одесса", 6000, customer1, "31312348");
        furnitureOrder2.calculatePrice();

        furnitureOrder2 = new FurnitureOrder("Книжная полка", new Date(), "Киев", "Одесса", 5000, customer1, "31312348");
        furnitureOrder2.calculatePrice();

        furnitureOrder2 = new FurnitureOrder("Книжная полка", new Date(), "Киев", "Одесса", 4000, customer1, "31312348");
        furnitureOrder2.calculatePrice();

        System.out.println();

        //test "validateOrder" ElectronicsOrder class

        //валидация
        //когда не подходящий город c которого заказивают
        //когда не подходящий город в которий заказивают
        //когда минимальная цена заказа < 100
        //когда пол "Мужской"

        ElectronicsOrder electronicsOrder1 = new ElectronicsOrder("Фен", new Date(), "Киев", "Днепр", 200, customer1, 2);
        electronicsOrder1.validateOrder();

        electronicsOrder1 = new ElectronicsOrder("Чайник", new Date(), "Львов", "Харьков", 200, customer1, 12);
        electronicsOrder1.validateOrder();

        electronicsOrder1 = new ElectronicsOrder("Чайник", new Date(), "Киев", "Львов", 200, customer1, 12);
        electronicsOrder1.validateOrder();

        electronicsOrder1 = new ElectronicsOrder("Чайник", new Date(), "Одесса", "Харьков", 50, customer1, 12);
        electronicsOrder1.validateOrder();

        electronicsOrder1 = new ElectronicsOrder("Чайник", new Date(), "Одесса", "Харьков", 200, customer2, 12);
        electronicsOrder1.validateOrder();

        System.out.println();

        //test "validateOrder" ElectronicsOrder class

        //расчет цены
        //расчет цены когда не подходящий город в которий производится доставка
        //расчет цены если сумма > 1000
        //расчет цены если сумма < 1000

        ElectronicsOrder electronicsOrder2 = new ElectronicsOrder("Фен", new Date(), "Киев", "Киев", 2000, customer1, 2);
        electronicsOrder2.calculatePrice();

        electronicsOrder2 = new ElectronicsOrder("Чайник", new Date(), "Днепр", "Харьков", 2000, customer1, 12);
        electronicsOrder2.calculatePrice();

        electronicsOrder2 = new ElectronicsOrder("Чайник", new Date(), "Киев", "Киев", 200, customer1, 12);
        electronicsOrder2.calculatePrice();

        electronicsOrder2 = new ElectronicsOrder("Чайник", new Date(), "Одесса", "Харьков", 200, customer1, 12);
        electronicsOrder2.calculatePrice();


    }
}
