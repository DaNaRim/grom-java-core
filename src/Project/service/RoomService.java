package Project.service;

import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Filter;
import Project.model.Room;

import java.io.IOException;
import java.util.ArrayList;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();
    private UserService userService = new UserService();

    public ArrayList<Room> findRooms(Filter filter) throws BadRequestException, IOException, BrokenFileException {
        if (filter == null) throw new BadRequestException("You have not selected options for filtering");

        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room) throws NotLogInException, NoAccessException, IOException, BrokenFileException {
        userService.checkLogin();
        userService.checkRights();

        return roomDAO.addRoom(room);
    }

    public void deleteRoom(long roomId) throws NotLogInException, NoAccessException, IOException, BrokenFileException {
        userService.checkLogin();
        userService.checkRights();

        roomDAO.deleteRoom(roomId);
    }
}