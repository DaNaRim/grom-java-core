package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Filter;
import Project.model.Room;
import Project.service.RoomService;

import java.util.ArrayList;

public class RoomController {
    private static RoomService roomService = new RoomService();

    public ArrayList<Room> findRooms(Filter filter) throws BadRequestException, InternalServerException {
        return roomService.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws NotLogInException, NoAccessException, InternalServerException, BadRequestException {
        return roomService.addRoom(room);
    }

    public void deleteRoom(long roomId) throws NotLogInException, NoAccessException, InternalServerException {
        roomService.deleteRoom(roomId);
    }
}