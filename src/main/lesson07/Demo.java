package main.lesson07;

import main.lesson06.Car;
import main.lesson06.DbConnector;
import main.lesson06.User;

public class Demo {

    public static void main(String[] args) {
        // two options to create object
        //class name (type) object name = new class name ();
        //class name (type) object name = new class name (attributes);

        User user = new User();
        User user1 = new User();
        User user2 = new User();

        User user3 = new User("Jack");

        Car car = new Car(10000, 2015, "Anton");
        //Car car1 = new Car();

        DbConnector dbConnector = new DbConnector();
    }
}
