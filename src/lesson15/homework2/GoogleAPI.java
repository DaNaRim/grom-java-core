package lesson15.homework2;

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
            if (room != null) {
                if (room.getPrice() == price && room.getPersons() == persons && room.getCityName() == city && room.getHotelName() == hotel)
                    numberOfRooms++;
            }
        }

        Room[] result = new Room[numberOfRooms];
        int i = 0;

        for (Room room : rooms) {
            if (room != null) {
                if (room.getPrice() == price && room.getPersons() == persons && room.getCityName() == city && room.getHotelName() == hotel) {
                    result[i] = room;
                    i++;
                }
            }
        }
        return result;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }
}
