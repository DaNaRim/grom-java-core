package lesson11.homework;

public class Controller {
    private API[] apis;

    public Controller(API[] apis) {
        this.apis = apis;
    }

    public Room[] requestRooms(int price, int persons, String city, String hotel) {
        //находит комнаты по заданным параметрам по всем АПИ

        int numberOfRooms = 0;

        for (API api : apis) {
            if (api == null)
                continue;

            for (Room room : api.findRooms(price, persons, city, hotel)) {
                if (room.getPrice() == price && room.getPersons() == persons && room.getCityName() == city && room.getHotelName() == hotel)
                    numberOfRooms++;
            }
        }

        Room[] rooms = new Room[numberOfRooms];
        int i = 0;

        for (API api : apis) {
            if (api == null)
                continue;

            for (Room room : api.findRooms(price, persons, city, hotel)) {
                if (room.getPrice() == price && room.getPersons() == persons && room.getCityName() == city && room.getHotelName() == hotel) {
                    rooms[i] = room;
                    i++;
                }
            }
        }
        return rooms;
    }

    public Room[] check(API api1, API api2) {
        //находит общие комнаты у двух API (в результирующий массив добавлять комнаты с api1).
        // Комнаты будем считать равными, если у них одинаковые все поля кроме id и dateAvailableFrom.
        // Гарантируется что id комнаты уникальный во всей системе

        //Зробити масив кімнат api1
        //Зробити масив кімнат api2
        //Проганти другий масив у прогині першого й знайти спільні кімнати

        int numberOfRooms = 0;
        for (Room room1 : api1.getAll()) {
            if (room1 == null)
                continue;
            for (Room room2 : api2.getAll()) {
                if (room2 == null)
                    continue;
                if (room1.getPrice() == room2.getPrice() && room1.getPersons() == room2.getPersons() && room1.getHotelName() == room2.getHotelName() && room1.getCityName() == room2.getCityName())
                    numberOfRooms++;
            }
        }

        Room[] rooms = new Room[numberOfRooms];
        int i = 0;

        for (Room room1 : api1.getAll()) {
            if (room1 == null)
                continue;
            for (Room room2 : api2.getAll()) {
                if (room2 == null)
                    continue;
                if (room1.getPrice() == room2.getPrice() && room1.getPersons() == room2.getPersons() && room1.getHotelName() == room2.getHotelName() && room1.getCityName() == room2.getCityName()) {
                    rooms[i] = room1;
                    i++;
                }
            }
        }
        return rooms;
    }

    public Room cheapestRoom() {
        //Находит самую дешевую комнаты среди всех комнат доступных в АПИ

        API cheapAPI = apis[0];
        Room cheapRoom = cheapAPI.getAll()[0];
        for (API api : apis) {
            for (Room room : api.getAll()) {
                if (room == null)
                    continue;
                if (cheapRoom.getPrice() < room.getPrice())
                    cheapRoom = room;
            }
        }
        return cheapRoom;
    }

}
