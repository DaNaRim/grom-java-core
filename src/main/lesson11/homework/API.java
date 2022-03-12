package main.lesson11.homework;

public interface API {

    Room[] findRooms(int price, int persons, String city, String hotel);

    Room[] getAll();
}
