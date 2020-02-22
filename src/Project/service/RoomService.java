package Project.service;

import Project.DAO.RoomDAO;
import Project.model.Filter;

import java.util.List;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public List findRooms(Filter filter) {
        //TODO check business logic

        return roomDAO.findRooms(filter);
    }
}