package project.demo;

import project.controller.RoomController;
import project.controller.UserController;
import project.model.Filter;
import project.model.Hotel;
import project.model.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoRoom {

    private static final RoomController roomController = new RoomController();
    private static final UserController userController = new UserController();

    public static void main(String[] args) throws Exception {

        userController.login("First", "SuperPassword");

        userController.login("Second", "SuperPassword2");

        Hotel hotel = new Hotel(5156100120976113778L, "T", "T", "T", "T");
        Room room = new Room(3, 150.40, false, true, new Date(), hotel);
        roomController.addRoom(room);

        Filter filter = new Filter(
                0,
                0.0,
                true,
                true,
                new SimpleDateFormat("dd.MM.yyyy").parse("25.02.2020"),
                "Ukraine",
                null);

        System.out.println(roomController.findRooms(filter).toString());

        roomController.deleteRoom(1778355955852921943L);
    }

}
