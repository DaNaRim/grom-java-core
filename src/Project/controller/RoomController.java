package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Filter;
import Project.model.Room;
import Project.service.RoomService;

import java.io.IOException;
import java.util.ArrayList;

public class RoomController {
    private RoomService roomService = new RoomService();

    public ArrayList<Room> findRooms(Filter filter) {
        ArrayList<Room> arrayList = null;
        try {
            arrayList = roomService.findRooms(filter);
            System.out.println("findRooms successful");
        } catch (BadRequestException | IOException | BrokenFileException e) {
            System.err.println("findRooms failed: " + e.getMessage());
        }
        return arrayList;
    }

    public Room addRoom(Room room) {
        Room room1 = null;
        try {
            room1 = roomService.addRoom(room);
            System.out.println("addRoom successful" + room1.getId());
        } catch (NotLogInException | NoAccessException | IOException | BrokenFileException e) {
            System.err.println("addRoom failed: " + e.getMessage());
        }
        return room1;
    }

    public void deleteRoom(long roomId) {
        try {
            roomService.deleteRoom(roomId);
            System.out.println("deleteRoom successful");
        } catch (NotLogInException | NoAccessException | IOException | BrokenFileException e) {
            System.err.println("deleteRoom failed: " + e.getMessage());
        }
    }
}