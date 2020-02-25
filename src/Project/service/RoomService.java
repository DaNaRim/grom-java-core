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

    public ArrayList<Room> findRooms(Filter filter)
            throws BadRequestException, IOException, BrokenFileException, NoAccessException {
        validateFilter(filter);
        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws NotLogInException, NoAccessException, IOException, BrokenFileException, BadRequestException {
        checkRoom(room);
        userService.checkLogin();
        userService.checkRights();
        return roomDAO.addRoom(room);
    }

    public void deleteRoom(long roomId) throws NotLogInException, NoAccessException, IOException, BrokenFileException {
        userService.checkLogin();
        userService.checkRights();
        roomDAO.deleteRoom(roomId);
    }

    private void validateFilter(Filter filter) throws BadRequestException {
        if (filter == null)
            throw new BadRequestException("validateFilter failed: you have not selected options for filtering");
    }

    private void checkRoom(Room room) throws BadRequestException {
        if (room.getNumberOfGuests() == 0 || room.getPrice() == 0 ||
                room.getDateAvailableFrom() == null || room.getHotel() == null)
            throw new BadRequestException("checkRoom failed: not all fields are filled");
    }
}