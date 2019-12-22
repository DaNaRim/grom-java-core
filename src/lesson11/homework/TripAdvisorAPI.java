package lesson11.homework;

public class TripAdvisorAPI implements API {
    private Room[] rooms;

    public TripAdvisorAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    //находит комнаты по заданным параметрам, а так же, количество гостей ищется в диапазоне +-1.
    //Например если пользователь ищет комнату с ко-вом гостей 3 и другими параметрами,
    //TripAdvisorAPI вернет все комнаты с ко-вом гостей от 2 до четырех

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
            if (room.getPrice() == price && room.getCityName() == city && room.getHotelName() == hotel) {
                if (room.getPersons() >= (persons - 1 > 0 ? persons : 0) && room.getPersons() <= persons + 1)
                    result = true;
            }
        }
        return result;
    }
}
