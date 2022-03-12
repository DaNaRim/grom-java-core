package main.lesson06;

public class Demo {

    public static void main(String[] args) {
        Car carObject = new Car(1000, 2015, "Anton");

        System.out.println(carObject.color);
        System.out.println(carObject.horsePower);
        System.out.println(carObject.ownerName);

        carObject.horsePower = 100;
        System.out.println(carObject.horsePower);

        carObject.startRun();
        carObject.stopRun();

        carObject.changeOwner("Test");
        System.out.println(carObject.ownerName);

        Car carObject2 = null;
        System.out.println(carObject2);
    }
}
