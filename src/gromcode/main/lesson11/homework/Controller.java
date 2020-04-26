package gromcode.main.lesson11.homework;

public class Controller {
    private API[] apis;

    public Controller(API[] apis) {
        this.apis = apis;
    }

    public Room[] requestRooms(int price, int persons, String city, String hotel) {
        //находит комнаты по заданным параметрам по всем АПИ

        Room[] result = new Room[countRooms(price, persons, city, hotel)];

        int index = 0;
        for (API api : apis) {
            if (api == null) continue;

            for (Room room : api.findRooms(price, persons, city, hotel)) {
                result[index] = room;
                index++;
            }
        }
        return result;
    }

    public Room[] check(API api1, API api2) {
        //находит общие комнаты у двух API (в результирующий массив добавлять комнаты с api1).
        // Комнаты будем считать равными, если у них одинаковые все поля кроме id и dateAvailableFrom.
        // Гарантируется что id комнаты уникальный во всей системе

        //Зробити масив кімнат api1
        //Зробити масив кімнат api2
        //Проганти другий масив у прогині першого й знайти спільні кімнати

        if (api1 == null || api2 == null) return null;

        Room[] result = new Room[countRooms(api1, api2)];

        int index = 0;
        for (Room room : api1.getAll()) {
            if (room == null) continue;

            for (Room room2 : api2.getAll()) {
                if (room.equals(room2)) {
                    result[index] = room;
                    index++;
                }
            }
        }
        return result;
    }

    public Room cheapestRoom() {
        //Находит самую дешевую комнаты среди всех комнат доступных в АПИ

        Room cheapRoom = apis[0].getAll()[0];
        for (API api : apis) {
            if (api == null) continue;

            for (Room room : api.getAll()) {
                if (room != null && cheapRoom.getPrice() > room.getPrice()) cheapRoom = room;
            }
        }
        return cheapRoom;
    }

    private int countRooms(int price, int persons, String city, String hotel) {
        int numberOfRooms = 0;

        for (API api : apis) {
            if (api != null) numberOfRooms += api.findRooms(price, persons, city, hotel).length;
        }
        return numberOfRooms;
    }

    private int countRooms(API api1, API api2) {
        int numberOfRooms = 0;
        for (Room room1 : api1.getAll()) {
            if (room1 == null) continue;

            for (Room room2 : api2.getAll()) {
                if (room1.equals(room2)) numberOfRooms++;
            }
        }
        return numberOfRooms;
    }
}