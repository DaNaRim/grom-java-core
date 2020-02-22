package gromcode.main.lesson11.homework;

public class BookingComAPI implements API {
    private Room[] rooms;

    public BookingComAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    //находит комнаты по заданным параметрам, а так же комнаты, которые по цене отличаются на 100 единиц в обе стороны.
    //Например если пользователь ищет комнату с ценой 50 и другими параметрами,
    //BookingComAPI вернет все комнаты с ценой в диапазоне 0 - 150

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {

        int numberOfRooms = 0;
        for (Room room : rooms) {
            if (room != null) {
                if (room.getPersons() == persons && room.getCityName() == city && room.getHotelName() == hotel) {
                    if (room.getPrice() >= (price - 100 > 0 ? price - 100 : 0) && room.getPrice() <= price + 100)
                        numberOfRooms++;
                }
            }
        }

        Room[] result = new Room[numberOfRooms];
        int i = 0;

        for (Room room : rooms) {
            if (room != null) {
                if (room.getPersons() == persons && room.getCityName() == city && room.getHotelName() == hotel) {
                    if (room.getPrice() >= (price - 100 > 0 ? price - 100 : 0) && room.getPrice() <= price + 100) {
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
