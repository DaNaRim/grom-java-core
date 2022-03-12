package main.lesson15.homework2;

public class GoogleAPI implements API {

    private Room[] rooms;

    public GoogleAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {

        Room[] result = new Room[countRooms(price, persons, city, hotel)];

        int index = 0;
        for (Room room : rooms) {
            if (room != null
                    && room.getPrice() == price
                    && room.getPersons() == persons
                    && room.getCityName().equals(city)
                    && room.getHotelName().equals(hotel)) {
                result[index] = room;
                index++;
            }
        }
        return result;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }

    private int countRooms(int price, int persons, String city, String hotel) {
        int numberOfRooms = 0;

        for (Room room : rooms) {
            if (room != null
                    && room.getPrice() == price
                    && room.getPersons() == persons
                    && room.getCityName().equals(city)
                    && room.getHotelName().equals(hotel)) {
                numberOfRooms++;
            }
        }
        return numberOfRooms;
    }
}
