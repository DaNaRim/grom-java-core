package Project.demo;

import Project.controller.RoomController;
import Project.model.Filter;
import Project.model.Hotel;
import Project.model.Room;

import java.util.Date;

public class DemoRoom {
    private static RoomController roomController = new RoomController();

    public static void main(String[] args) {
        Filter filter = new Filter(4, 400.0, false, false, new Date(),
                "Spain", "testCity");

        roomController.findRooms(filter);

        Hotel hotel = new Hotel(333L, "Orange", "Ukraine", "Cherkassy", "Rose");
        Room room = new Room(55L, 1, 100.40, true, true,
                new Date(), hotel);

        System.out.println(roomController.addRoom(room));

        roomController.deleteRoom(room.getId());
    }
}