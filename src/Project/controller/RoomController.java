package Project.controller;

import Project.model.Filter;
import Project.service.RoomService;

import java.util.List;

public class RoomController {
    private RoomService roomService = new RoomService();

    public List findRooms(Filter filter) {
        return roomService.findRooms(filter);
    }
}