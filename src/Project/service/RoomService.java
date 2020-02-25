package Project.service;

import Project.DAO.HotelDAO;
import Project.DAO.RoomDAO;
import Project.exception.*;
import Project.model.Filter;
import Project.model.Room;

import java.io.IOException;
import java.util.ArrayList;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();
    private UserService userService = new UserService();
    private HotelDAO hotelDAO = new HotelDAO();

    public ArrayList<Room> findRooms(Filter filter)
            throws BadRequestException, IOException, BrokenFileException, NoAccessException {
        validateFilter(filter);
        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws NotLogInException, NoAccessException, IOException, InternalServerException, BadRequestException {
        checkRoom(room);
        userService.checkLogin();
        userService.checkRights();
        return roomDAO.addRoom(room);
    }

    public void deleteRoom(long roomId)
            throws NotLogInException, NoAccessException, IOException, InternalServerException {
        userService.checkLogin();
        userService.checkRights();
        roomDAO.deleteRoom(roomId);
    }

    private void validateFilter(Filter filter) throws BadRequestException {
        if (filter == null)
            throw new BadRequestException("validateFilter failed: you have not selected options for filtering");
    }

    private void checkRoom(Room room)
            throws BadRequestException, InternalServerException, IOException, NoAccessException {
        if (room.getNumberOfGuests() == 0 || room.getPrice() == 0.0 || room.getBreakfastIncluded() == null ||
                room.getPetsAllowed() == null || room.getDateAvailableFrom() == null || room.getHotel() == null)
            throw new BadRequestException("checkRoom failed: not all fields are filled");
        hotelDAO.findById(room.getHotel().getId());
    }
}