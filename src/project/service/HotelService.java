package project.service;

import project.DAO.HotelDAO;
import project.DAO.RoomDAO;
import project.exception.*;
import project.model.Hotel;

import java.util.ArrayList;
import java.util.LinkedList;

public class HotelService {

    private static final UserService userService = new UserService();
    private static final HotelDAO hotelDAO = new HotelDAO();
    private static final RoomDAO roomDAO = new RoomDAO();

    public ArrayList<Hotel> findHotelByName(String name)
            throws BadRequestException, InternalServerException, NotFoundException {

        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new BadRequestException("findHotelByName failed: the field is not filled correctly");
        }
        return hotelDAO.findHotelByName(name);
    }

    public ArrayList<Hotel> findHotelByCity(String city)
            throws BadRequestException, InternalServerException, NotFoundException {

        if (city == null || city.isEmpty() || city.isBlank()) {
            throw new BadRequestException("findHotelByCity failed: the field is not filled correctly");
        }
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel)
            throws NoAccessException, BadRequestException, InternalServerException, NotLogInException {
        userService.checkForAdminPermissions();
        validateHotel(hotel);

        return hotelDAO.save(hotel);
    }

    public void deleteHotel(long hotelId)
            throws NoAccessException, BadRequestException, InternalServerException, NotLogInException, NotFoundException {
        userService.checkForAdminPermissions();

        if (!hotelDAO.isExists(hotelId)) {
            throw new BadRequestException("deleteHotel failed: missing hotel with id: " + hotelId);
        }
        if (roomDAO.hasRooms(hotelId)) {
            throw new BadRequestException("deleteHotel failed: hotel " + hotelId + " has rooms. First delete it");
        }
        hotelDAO.delete(hotelId);
    }

    private void validateHotel(Hotel hotel) throws BadRequestException, InternalServerException {
        if (hotel == null) {
            throw new BadRequestException("validateHotel failed: impossible to process null hotel");
        }
        if (hotel.getName() == null
                || hotel.getCity() == null
                || hotel.getCountry() == null
                || hotel.getStreet() == null
                || hotel.getName().isEmpty()
                || hotel.getCity().isEmpty()
                || hotel.getCountry().isEmpty()
                || hotel.getStreet().isEmpty()) {
            throw new BadRequestException("validateHotel failed: not all fields are filled");
        }
        if (hotel.getName().isBlank()
                || hotel.getCountry().isBlank()
                || hotel.getCity().isBlank()
                || hotel.getStreet().isBlank()) {
            throw new BadRequestException("validateHotel failed: fields must not contain spaces");
        }
        if (hotel.getName().contains(", ")
                || hotel.getCountry().contains(", ")
                || hotel.getCity().contains(", ")
                || hotel.getStreet().contains(", ")) {
            throw new BadRequestException("validateHotel failed: fields must not contain ', '");
        }
        if (hotelDAO.isHotelExist(hotel)) {
            throw new BadRequestException("validateHotel failed: the hotel with this parameters already exist");
        }
    }

}
