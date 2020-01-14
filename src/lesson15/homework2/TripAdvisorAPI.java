package lesson15.homework2;

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
            if (room != null) {
                if (room.getPrice() == price && room.getCityName() == city && room.getHotelName() == hotel) {
                    if (room.getPersons() >= (persons - 1 > 1 ? persons : 1) && room.getPersons() <= persons + 1)
                        numberOfRooms++;
                }
            }
        }

        Room[] result = new Room[numberOfRooms];
        int i = 0;

        for (Room room : rooms) {
            if (room != null) {
                if (room.getPrice() == price && room.getCityName() == city && room.getHotelName() == hotel) {
                    if (room.getPersons() >= (persons - 1 > 1 ? persons : 1) && room.getPersons() <= persons + 1) {
                        result[i] = room;
                        i++;
                    }
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
