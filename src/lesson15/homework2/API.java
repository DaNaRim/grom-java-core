package lesson15.homework2;

public interface API {

    Room[] findRooms(int price, int persons, String city, String hotel);

    Room[] getAll();
}
