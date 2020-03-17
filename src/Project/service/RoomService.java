package Project.service;

import Project.DAO.HotelDAO;
import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.Filter;
import Project.model.Room;

import java.util.LinkedList;

public class RoomService {
    private static RoomDAO roomDAO;
    private static UserService userService = new UserService();
    private static HotelDAO hotelDAO;

    static {
        try {
            roomDAO = new RoomDAO();
            hotelDAO = new HotelDAO();
        } catch (BrokenFileException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Room> findRooms(Filter filter) throws InternalServerException, BadRequestException {
        validateFilter(filter);
        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room) throws InternalServerException, BadRequestException, NoAccessException {
        userService.checkAccess();
        validateRoom(room);
        return roomDAO.addObjectToDAO(room);
    }

    public void deleteRoom(long roomId) throws InternalServerException, NoAccessException, BadRequestException {
        userService.checkAccess();
        Room room = roomDAO.findById(roomId);
        roomDAO.deleteObjectFromDAO(room);
    }

    private void validateFilter(Filter filter) throws BadRequestException {
        if (filter == null ||
                (filter.getNumberOfGuests() == 0 && filter.getPrice() == 0 &&
                        filter.getBreakfastIncluded() == null && filter.getPetsAllowed() == null &&
                        filter.getDateAvailableFrom() == null && filter.getCountry() == null &&
                        filter.getCity() == null))
            throw new BadRequestException("validateFilter failed: you have not selected any options for filtering");

        if (filter.getNumberOfGuests() < 0 && filter.getPrice() < 0)
            throw new BadRequestException("validateFilter failed: you have not selected correct options for filtering");
    }

    private void validateRoom(Room room) throws InternalServerException, BadRequestException {
        if (room == null)
            throw new BadRequestException("validateRoom failed: impossible to process null room");

        if (room.getNumberOfGuests() <= 0 || room.getPrice() <= 0.0 || room.getBreakfastIncluded() == null ||
                room.getPetsAllowed() == null || room.getDateAvailableFrom() == null || room.getHotel() == null)
            throw new BadRequestException("validateRoom failed: not all fields are filled correctly");
        hotelDAO.findById(room.getHotel().getId());
    }
}