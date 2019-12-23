package lesson11.homework;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        Room[] rooms = new Room[5];
        rooms[0] = new Room(478662, 50, 1, new Date(), "Orange", "Cherkassy");
        rooms[1] = new Room(257861, 150, 2, new Date(), "Orange", "Cherkassy");
        rooms[2] = new Room(678621, 250, 3, new Date(), "Orange", "Cherkassy");
        rooms[3] = new Room(278672, 1, 4, new Date(), "Orange", "Cherkassy");
        rooms[4] = null;
        Room[] rooms2 = new Room[5];
        rooms2[0] = new Room(122751, 50, 1, new Date(), "Orange", "Cherkassy");
        rooms2[1] = new Room(124531, 150, 2, new Date(), "Orange", "Cherkassy");
        rooms2[2] = new Room(122141, 250, 3, new Date(), "Orange", "Cherkassy");
        rooms2[3] = new Room(335553, 45, 4, new Date(), "Orange", "Cherkassy");
        rooms2[4] = null;
        Room[] rooms3 = new Room[5];
        rooms3[0] = new Room(124421, 50, 1, new Date(), "Orange", "Cherkassy");
        rooms3[1] = new Room(138975, 150, 2, new Date(), "Orange", "Cherkassy");
        rooms3[2] = new Room(178321, 250, 3, new Date(), "Orange", "Cherkassy");
        rooms3[3] = new Room(444444, 1, 4, new Date(), "Orange", "Cherkassy");
        rooms3[4] = null;

        GoogleAPI googleAPI = new GoogleAPI(rooms);
        TripAdvisorAPI tripAdvisorAPI = new TripAdvisorAPI(rooms2);
        BookingComAPI bookingComAPI = new BookingComAPI(rooms3);

        Controller controller = new Controller(new API[]{googleAPI, tripAdvisorAPI, null});
        for (Room room : controller.check(googleAPI, tripAdvisorAPI)) {
            System.out.println(room.getId());
        }

        System.out.println();

        for (Room room : controller.requestRooms(50, 2, "Cherkassy", "Orange")) {
            System.out.println(room.getId());
        }

        System.out.println();

        TripAdvisorAPI tripAdvisorAPI1 = new TripAdvisorAPI(rooms);
        for (Room room : tripAdvisorAPI1.findRooms(100, 2, "Cherkassy", "Orange")) {
            System.out.println(room.getId());
        }

        System.out.println();

        GoogleAPI googleAPI1 = new GoogleAPI(rooms);
        for (Room room : googleAPI1.findRooms(150, 2, "Cherkassy", "Orange")) {
            System.out.println(room.getId());
        }

        System.out.println();

        BookingComAPI bookingComAPI1 = new BookingComAPI(rooms);
        for (Room room : bookingComAPI1.findRooms(150, 2, "Cherkassy", "Orange")) {
            System.out.println(room.getId());
        }

    }
}
