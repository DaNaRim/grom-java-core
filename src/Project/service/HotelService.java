package Project.service;

import Project.DAO.HotelDAO;
import Project.DAO.RoomDAO;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.Hotel;
import Project.model.Room;

import java.util.LinkedList;

public class HotelService {
    private static HotelDAO hotelDAO = new HotelDAO();
    private static UserService userService = new UserService();
    private static RoomDAO roomDAO = new RoomDAO();

    public LinkedList<Hotel> findHotelByName(String name) throws BadRequestException, InternalServerException {
        checkName(name);
        return hotelDAO.findHotelByName(name);
    }

    public LinkedList<Hotel> findHotelByCity(String city) throws BadRequestException, InternalServerException {
        checkCity(city);
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) throws NoAccessException, BadRequestException, InternalServerException {
        userService.checkAccess();
        checkHotel(hotel);
        isExist(hotel);
        return hotelDAO.addHotel(hotel);
    }

    public void deleteHotel(long hotelId) throws NoAccessException, BadRequestException, InternalServerException {
        userService.checkAccess();
        hotelDAO.findById(hotelId);
        checkHotelRooms(hotelId);
        hotelDAO.deleteHotel(hotelId);
    }

    private void checkName(String name) throws BadRequestException {
        if (name == null)
            throw new BadRequestException("checkName failed: the field is not filled");
    }

    private void checkCity(String city) throws BadRequestException {
        if (city == null)
            throw new BadRequestException("checkCity failed: the field is not filled");
    }

    private void checkHotel(Hotel hotel) throws BadRequestException {
        if (hotel == null)
            throw new BadRequestException("checkUser failed: impossible to process null hotel");

        if (hotel.getName() == null || hotel.getCity() == null ||
                hotel.getCountry() == null || hotel.getStreet() == null)
            throw new BadRequestException("checkHotel failed: not all fields are filled");
    }

    private void isExist(Hotel hotel) throws InternalServerException, BadRequestException {
        for (Hotel hotel1 : hotelDAO.getObjectsFromDAO()) {
            if (hotel1.equals(hotel))
                throw new BadRequestException("isExist failed: the hotel is already exist: " + hotel1.getId());
        }
    }

    private void checkHotelRooms(long hotelId) throws InternalServerException, BadRequestException {
        for (Room room : roomDAO.getObjectsFromDAO()) {
            if (room.getHotel().getId().equals(hotelId))
                throw new BadRequestException("checkHotelRooms failed: This hotel has a room that is in use: " +
                        room.getId());
        }
    }
}