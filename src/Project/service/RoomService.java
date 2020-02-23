package Project.service;

import Project.DAO.RoomDAO;
import Project.model.Filter;
import Project.model.Room;

import java.util.List;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public List findRooms(Filter filter) {
        //TODO check business logic

        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room) {
        //TODO check business logic

        return roomDAO.addRoom(room);
    }

    public void deleteRoom(long roomId) {
        //TODO check business logic

        roomDAO.deleteRoom(roomId);
    }
}