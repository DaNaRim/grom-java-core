package gromcode.main.lesson15.homework2;

public class TripAdvisorAPI implements API {
    private Room[] rooms;

    public TripAdvisorAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {
        Room[] result = new Room[countRooms(price, persons, city, hotel)];

        int index = 0;
        for (Room room : rooms) {
            if (room != null && room.getPrice() == price && room.getCityName().equals(city) &&
                    room.getHotelName().equals(hotel) && room.getPersons() >= (persons - 1 > 1 ? persons : 1) &&
                    room.getPersons() <= persons + 1) {
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
            if (room != null && room.getPrice() == price && room.getCityName().equals(city) &&
                    room.getHotelName().equals(hotel) && room.getPersons() >= (persons - 1 > 1 ? persons : 1) &&
                    room.getPersons() <= persons + 1) {
                numberOfRooms++;
            }
        }
        return numberOfRooms;
    }
}