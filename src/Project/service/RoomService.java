package Project.service;

import Project.DAO.RoomDAO;
import Project.exception.NoAccessException;
import Project.exception.NotRegisteredException;
import Project.model.Filter;
import Project.model.Room;
import Project.model.UserType;

import java.util.List;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public List findRooms(Filter filter) {
        //TODO check business logic

        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room) throws Exception {
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("You don`t have enough rights");

        return roomDAO.addRoom(room);
    }

    public void deleteRoom(long roomId) throws Exception {
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("You don`t have enough rights");

        roomDAO.deleteRoom(roomId);
    }
}