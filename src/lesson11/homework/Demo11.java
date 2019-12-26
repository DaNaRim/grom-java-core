package lesson11.homework;

import java.util.Arrays;
import java.util.Date;

public class Demo11 {
    public static void main(String[] args) {

        Room[] notNullRooms = new Room[8];
        notNullRooms[0] = new Room(1001, 150, 1, new Date(), "Orange", "Cherkassy");
        notNullRooms[1] = new Room(1002, 150, 2, new Date(), "Orange", "Cherkassy");
        notNullRooms[2] = new Room(1003, 150, 3, new Date(), "Orange", "Cherkassy");
        notNullRooms[3] = new Room(1004, 150, 0, new Date(), "Orange", "Cherkassy");
        notNullRooms[4] = new Room(1005, 1000, 8, new Date(), "Orange", "Cherkassy");

        notNullRooms[5] = new Room(1031, 100, 6, new Date(), "Orange", "Cherkassy");
        notNullRooms[6] = new Room(1032, 150, 6, new Date(), "Orange", "Cherkassy");
        notNullRooms[7] = new Room(1033, 250, 6, new Date(), "Orange", "Cherkassy");

        Room[] rooms = new Room[5];
        rooms[0] = new Room(1011, 50, 1, new Date(), "Orange", "Cherkassy");
        rooms[1] = new Room(1012, 150, 2, new Date(), "Orange", "Cherkassy");
        rooms[2] = null;
        rooms[3] = new Room(1014, 250, 3, new Date(), "Orange", "Cherkassy");
        rooms[4] = new Room(1015, 45, 4, new Date(), "Orange", "Cherkassy");
        Room[] nullRooms = {null, null, null};

        //test "findRooms" TripAdvisorAPI class

        //точний поиск комнат +
        //поиск комнат с количеством гостей в диапазоне +-1 +
        //когда нет подходящих комнат по количеству человек +
        //когда нет подходящих комнат по стоимости +
        //когда нет подходящих комнат по городу +
        //когда нет подходящих комнат по названию +
        //когда room = null +
        //когда rooms = null +

        TripAdvisorAPI tripAdvisorAPI = new TripAdvisorAPI(notNullRooms);
        for (Room room : tripAdvisorAPI.findRooms(1000, 8, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : tripAdvisorAPI.findRooms(150, 2, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : tripAdvisorAPI.findRooms(555, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : tripAdvisorAPI.findRooms(100, 9, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : tripAdvisorAPI.findRooms(150, 2, "TestCity", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : tripAdvisorAPI.findRooms(150, 3, "Cherkassy", "TestName"))
            System.out.println(room.getId());
        System.out.println("////");

        tripAdvisorAPI = new TripAdvisorAPI(rooms);
        for (Room room : tripAdvisorAPI.findRooms(250, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        tripAdvisorAPI = new TripAdvisorAPI(nullRooms);
        for (Room room : tripAdvisorAPI.findRooms(150, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        //test "findRooms" BookingComAPI class

        //точний поиск комнат +
        //поиск комнат с ценой в диапазоне +-100 +
        //когда нет подходящих комнат по стоимости +
        //когда нет подходящих комнат по количеству человек +
        //когда нет подходящих комнат по городу +
        //когда нет подходящих комнат по названию +
        //когда room = null +
        //когда rooms = null +

        BookingComAPI bookingComAPI = new BookingComAPI(notNullRooms);
        for (Room room : bookingComAPI.findRooms(1000, 8, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : bookingComAPI.findRooms(150, 6, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : bookingComAPI.findRooms(555, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : bookingComAPI.findRooms(100, 9, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : bookingComAPI.findRooms(150, 2, "TestCity", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : bookingComAPI.findRooms(150, 3, "Cherkassy", "TestName"))
            System.out.println(room.getId());
        System.out.println("////");

        bookingComAPI = new BookingComAPI(rooms);
        for (Room room : bookingComAPI.findRooms(250, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        bookingComAPI = new BookingComAPI(nullRooms);
        for (Room room : bookingComAPI.findRooms(150, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        //test "findRooms" GoogleAPI class

        //точний поиск комнат +
        //когда нет подходящих комнат по стоимости +
        //когда нет подходящих комнат по количеству человек +
        //когда нет подходящих комнат по городу +
        //когда нет подходящих комнат по названию +
        //когда room = null +
        //когда rooms = null +

        GoogleAPI googleAPI = new GoogleAPI(notNullRooms);
        for (Room room : googleAPI.findRooms(1000, 8, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : googleAPI.findRooms(150, 2, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : googleAPI.findRooms(555, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : googleAPI.findRooms(100, 9, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : googleAPI.findRooms(150, 2, "TestCity", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        for (Room room : googleAPI.findRooms(150, 3, "Cherkassy", "TestName"))
            System.out.println(room.getId());
        System.out.println("////");

        googleAPI = new GoogleAPI(rooms);
        for (Room room : googleAPI.findRooms(250, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        googleAPI = new GoogleAPI(nullRooms);
        for (Room room : googleAPI.findRooms(150, 3, "Cherkassy", "Orange"))
            System.out.println(room.getId());
        System.out.println("////");

        //test "requestRooms" Controller class

        //находит комнаты по заданным параметрам по всем АПИ
        //когда API null
        //когда room налл

        tripAdvisorAPI = new TripAdvisorAPI(notNullRooms);
        bookingComAPI = new BookingComAPI(notNullRooms);
        googleAPI = new GoogleAPI(notNullRooms);
        API[] api = {tripAdvisorAPI, bookingComAPI, googleAPI};
        Controller controller = new Controller(api);

        System.out.println(Arrays.toString(controller.requestRooms(150, 6, "Cherkassy", "Orange")));

        api = new API[]{tripAdvisorAPI, null, googleAPI};
        controller = new Controller(api);
        System.out.println(Arrays.toString(controller.requestRooms(150, 6, "Cherkassy", "Orange")));

        tripAdvisorAPI = new TripAdvisorAPI(rooms);
        bookingComAPI = new BookingComAPI(rooms);
        googleAPI = new GoogleAPI(rooms);
        api = new API[]{tripAdvisorAPI, bookingComAPI, googleAPI};
        controller = new Controller(api);

        System.out.println(Arrays.toString(controller.requestRooms(150, 6, "Cherkassy", "Orange")));
        System.out.println("//////////////");

        //test "check" Controller class

        //находит общие комнаты
        //когда API null
        //когда room налл

        tripAdvisorAPI = new TripAdvisorAPI(notNullRooms);
        bookingComAPI = new BookingComAPI(notNullRooms);
        googleAPI = new GoogleAPI(notNullRooms);
        API[] api2 = {tripAdvisorAPI, bookingComAPI, googleAPI};
        Controller controller2 = new Controller(api2);

        System.out.println(Arrays.toString(controller2.check(tripAdvisorAPI, bookingComAPI)));

        api2 = new API[]{tripAdvisorAPI, null, googleAPI};
        controller2 = new Controller(api2);
        System.out.println(Arrays.toString(controller2.check(tripAdvisorAPI, null)));

        tripAdvisorAPI = new TripAdvisorAPI(rooms);
        bookingComAPI = new BookingComAPI(rooms);
        googleAPI = new GoogleAPI(rooms);
        api2 = new API[]{tripAdvisorAPI, bookingComAPI, googleAPI};
        controller2 = new Controller(api2);

        System.out.println(Arrays.toString(controller2.check(googleAPI, bookingComAPI)));

        //test "cheapestRoom" Controller class

        //Находит самую дешевую комнаты
        //когда API null
        //когда room налл

        tripAdvisorAPI = new TripAdvisorAPI(notNullRooms);
        bookingComAPI = new BookingComAPI(notNullRooms);
        googleAPI = new GoogleAPI(notNullRooms);
        API[] api3 = {tripAdvisorAPI, bookingComAPI, googleAPI};
        Controller controller3 = new Controller(api3);

        System.out.println(controller3.cheapestRoom());

        api3 = new API[]{tripAdvisorAPI, null, googleAPI};
        controller3 = new Controller(api3);
        System.out.println(controller3.cheapestRoom());

        tripAdvisorAPI = new TripAdvisorAPI(rooms);
        bookingComAPI = new BookingComAPI(rooms);
        googleAPI = new GoogleAPI(rooms);
        api3 = new API[]{tripAdvisorAPI, bookingComAPI, googleAPI};
        controller3 = new Controller(api3);

        System.out.println(controller3.cheapestRoom());

    }
}
