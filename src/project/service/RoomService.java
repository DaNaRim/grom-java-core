package project.service;

import project.DAO.HotelDAO;
import project.DAO.RoomDAO;
import project.exception.*;
import project.model.Filter;
import project.model.Room;

import java.util.ArrayList;
import java.util.LinkedList;

public class RoomService {

    private static final RoomDAO roomDAO = new RoomDAO();
    private static final UserService userService = new UserService();
    private static final HotelDAO hotelDAO = new HotelDAO();

    public ArrayList<Room> findRooms(Filter filter)
            throws InternalServerException, BadRequestException, NotFoundException {
        validateFilter(filter);
        return roomDAO.findRooms(filter);
    }

    public Room addRoom(Room room)
            throws InternalServerException, BadRequestException, NoAccessException, NotLogInException {
        userService.checkForAdminPermissions();
        validateRoom(room);
        return roomDAO.save(room);
    }

    public void deleteRoom(long roomId)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException,
            NotFoundException {
        userService.checkForAdminPermissions();

        if (!roomDAO.isExists(roomId)) {
            throw new BadRequestException("deleteRoom failed: missing room with id: " + roomId);
        }
        roomDAO.delete(roomId);
    }

    //TODO test wrappers
    private void validateFilter(Filter filter) throws BadRequestException {
        if (filter == null
                || (filter.getNumberOfGuests() == null
                && filter.getPrice() == null
                && filter.getBreakfastIncluded() == null
                && filter.getPetsAllowed() == null
                && filter.getDateAvailableFrom() == null
                && filter.getCountry() == null
                && filter.getCity() == null)) {
            throw new BadRequestException("validateFilter failed: you have not selected any options for filtering");
        }
        if (filter.getNumberOfGuests() < 0 && filter.getPrice() < 0) {
            throw new BadRequestException("validateFilter failed: you have not selected correct options for filtering");
        }
    }

    //TODO test wrappers
    private void validateRoom(Room room) throws InternalServerException, BadRequestException {
        if (room == null) {
            throw new BadRequestException("validateRoom failed: impossible to process null room");
        }
        if (room.getNumberOfGuests() < 1
                || room.getPrice() <= 0.0
                || room.getBreakfastIncluded() == null
                || room.getPetsAllowed() == null
                || room.getDateAvailableFrom() == null
                || room.getHotel() == null) {
            throw new BadRequestException("validateRoom failed: not all fields are filled correctly");
        }
        if (hotelDAO.isExists(room.getHotel().getId())) {
            throw new BadRequestException("validateRoom failed: missing hotel with id: " + room.getHotel().getId());
        }
    }

}
