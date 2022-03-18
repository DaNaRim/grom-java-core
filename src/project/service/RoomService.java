package project.service;

import project.DAO.HotelDAO;
import project.DAO.RoomDAO;
import project.exception.*;
import project.model.Filter;
import project.model.Room;

import java.util.LinkedList;

public class RoomService {
    private static RoomDAO roomDAO = new RoomDAO();
    private static UserService userService = new UserService();
    private static HotelDAO hotelDAO = new HotelDAO();

    public LinkedList<Room> findRooms(Filter filter)
            throws InternalServerException, BadRequestException, NotFoundException {
        validateFilter(filter);
        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws InternalServerException, BadRequestException, NoAccessException, NotLogInException {
        userService.checkAccess();
        validateRoom(room);
        return roomDAO.addObjectToDAO(room);
    }

    public void deleteRoom(long roomId)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException {
        userService.checkAccess();
        Room room = roomDAO.findById(roomId);
        roomDAO.deleteObjectFromDAO(room);
    }

    private void validateFilter(Filter filter) throws BadRequestException {
        if (filter == null ||
                (filter.getNumberOfGuests() == 0 && filter.getPrice() == 0 &&
                        filter.getBreakfastIncluded() == null && filter.getPetsAllowed() == null &&
                        filter.getDateAvailableFrom() == null && filter.getCountry() == null &&
                        filter.getCity() == null)) {
            throw new BadRequestException("validateFilter failed: you have not selected any options for filtering");
        }
        if (filter.getNumberOfGuests() < 0 && filter.getPrice() < 0) {
            throw new BadRequestException("validateFilter failed: you have not selected correct options for filtering");
        }
    }

    private void validateRoom(Room room) throws InternalServerException, BadRequestException {
        if (room == null) {
            throw new BadRequestException("validateRoom failed: impossible to process null room");
        }
        if (room.getNumberOfGuests() <= 0 || room.getPrice() <= 0.0 || room.getBreakfastIncluded() == null ||
                room.getPetsAllowed() == null || room.getDateAvailableFrom() == null || room.getHotel() == null) {
            throw new BadRequestException("validateRoom failed: not all fields are filled correctly");
        }
        hotelDAO.findById(room.getHotel().getId());
    }
}