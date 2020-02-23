package Project.service;

import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotRegisteredException;
import Project.model.Filter;
import Project.model.Room;
import Project.model.UserType;

import java.io.IOException;
import java.util.ArrayList;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public ArrayList<Room> findRooms(Filter filter) throws BadRequestException, IOException, BrokenFileException {
        if (filter == null)
            throw new BadRequestException("You have not selected options for filtering");

        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room) throws NotRegisteredException, NoAccessException, IOException, BrokenFileException {
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("You don`t have enough rights");

        return roomDAO.addRoom(room);
    }

    public void deleteRoom(long roomId)
            throws NotRegisteredException, NoAccessException, IOException, BrokenFileException {

        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("You don`t have enough rights");

        roomDAO.deleteRoom(roomId);
    }
}