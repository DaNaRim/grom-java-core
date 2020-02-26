package Project.service;

import Project.DAO.HotelDAO;
import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Filter;
import Project.model.Room;

import java.util.LinkedList;

public class RoomService {
    private static RoomDAO roomDAO = new RoomDAO();
    private static UserService userService = new UserService();
    private static HotelDAO hotelDAO = new HotelDAO();

    public LinkedList<Room> findRooms(Filter filter) throws InternalServerException, BadRequestException {
        validateFilter(filter);
        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws InternalServerException, BadRequestException, NotLogInException, NoAccessException {
        userService.checkRights();
        checkRoom(room);
        return roomDAO.addRoom(room);
    }

    public void deleteRoom(long roomId)
            throws InternalServerException, NoAccessException, NotLogInException, BadRequestException {
        userService.checkRights();
        roomDAO.findById(roomId);
        roomDAO.deleteRoom(roomId);
    }

    private void validateFilter(Filter filter) throws BadRequestException {
        if (filter == null)
            throw new BadRequestException("validateFilter failed: you have not selected options for filtering");
    }

    private void checkRoom(Room room) throws InternalServerException, BadRequestException {
        if (room == null)
            throw new BadRequestException("checkUser failed: impossible to process null room");

        if (room.getNumberOfGuests() <= 0 || room.getPrice() <= 0.0 || room.getBreakfastIncluded() == null ||
                room.getPetsAllowed() == null || room.getDateAvailableFrom() == null || room.getHotel() == null)
            throw new BadRequestException("checkRoom failed: not all fields are filled correctly");
        hotelDAO.findById(room.getHotel().getId());
    }
}