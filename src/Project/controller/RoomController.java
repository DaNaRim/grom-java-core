package Project.controller;

import Project.model.Filter;
import Project.model.Room;
import Project.service.RoomService;

import java.util.List;

public class RoomController {
    private RoomService roomService = new RoomService();

    public List findRooms(Filter filter) {
        return roomService.findRooms(filter);
    }

    public Room addRoom(Room room) {
        return roomService.addRoom(room);
    }

    public void deleteRoom(long roomId) {
        roomService.deleteRoom(roomId);
    }
}