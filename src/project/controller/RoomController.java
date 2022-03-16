package project.controller;

import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.exception.NoAccessException;
import project.model.Filter;
import project.model.Room;
import project.service.RoomService;

import java.util.LinkedList;

public class RoomController {
    private static RoomService roomService = new RoomService();

    public LinkedList<Room> findRooms(Filter filter) throws BadRequestException, InternalServerException {
        return roomService.findRooms(filter);
    }

    public Room addRoom(Room room) throws NoAccessException, InternalServerException, BadRequestException {
        return roomService.addRoom(room);
    }

    public void deleteRoom(long roomId) throws NoAccessException, InternalServerException, BadRequestException {
        roomService.deleteRoom(roomId);
    }
}