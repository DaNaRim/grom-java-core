package Project.controller;

import Project.exception.*;
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
            throws NotLogInException, NoAccessException, InternalServerException, IOException, BadRequestException {
        return roomService.addRoom(room);
    }

    public void deleteRoom(long roomId)
            throws NotLogInException, NoAccessException, InternalServerException, IOException {
        roomService.deleteRoom(roomId);
    }
}