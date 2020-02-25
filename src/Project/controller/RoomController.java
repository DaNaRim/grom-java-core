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

    public ArrayList<Room> findRooms(Filter filter)
            throws BadRequestException, NoAccessException, BrokenFileException, IOException {
        return roomService.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws NotLogInException, NoAccessException, BrokenFileException, IOException, BadRequestException {
        return roomService.addRoom(room);
    }

    public void deleteRoom(long roomId) throws NotLogInException, NoAccessException, BrokenFileException, IOException {
        roomService.deleteRoom(roomId);
    }
}