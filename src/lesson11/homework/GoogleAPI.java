package lesson11.homework;

public class GoogleAPI implements API {
    private Room[] rooms;

    public GoogleAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    //ищет сторого по заданным параметрам

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        int numberOfRooms = 0;
        for (Room room : rooms) {
            if (RoomCheck(room, price, persons, city, hotel))
                numberOfRooms++;
        }

        Room[] rooms = new Room[numberOfRooms];
        int i = 0;

        for (Room room : rooms) {
            if (RoomCheck(room, price, persons, city, hotel)) {
                rooms[i] = room;
                i++;
            }
        }
        return rooms;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }

    private boolean RoomCheck(Room room, int price, int persons, String city, String hotel) {

        boolean result = false;

        if (room != null) {
            if (room.getPrice() == price && room.getPersons() == persons && room.getCityName() == city && room.getHotelName() == hotel)
                result = true;
        }
        return result;
    }
}
