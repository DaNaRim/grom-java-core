package project.controller;

import project.exception.*;
import project.model.Filter;
import project.model.Room;
import project.service.RoomService;

import java.util.LinkedList;

public class RoomController {
    private static RoomService roomService = new RoomService();

    public LinkedList<Room> findRooms(Filter filter)
            throws BadRequestException, InternalServerException, NotFoundException {
        return roomService.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws NoAccessException, InternalServerException, BadRequestException, NotLogInException {
        return roomService.addRoom(room);
    }

    public void deleteRoom(long roomId)
            throws NoAccessException, InternalServerException, BadRequestException, NotLogInException {
        roomService.deleteRoom(roomId);
    }
}