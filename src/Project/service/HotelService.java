package Project.service;

import Project.DAO.HotelDAO;
import Project.exception.*;
import Project.model.Hotel;

import java.io.IOException;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();
    private UserService userService = new UserService();

    public Hotel findHotelByName(String name)
            throws BadRequestException, IOException, BrokenFileException, NoAccessException {
        checkName(name);
        return hotelDAO.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city)
            throws BadRequestException, IOException, BrokenFileException, NoAccessException {
        checkCity(city);
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel)
            throws NotLogInException, NoAccessException, BadRequestException, BrokenFileException, IOException {
        checkHotel(hotel);
        userService.checkLogin();
        userService.checkRights();
        return hotelDAO.addHotel(hotel);
    }

    public void deleteHotel(long hotelId)
            throws NotLogInException, NoAccessException, BadRequestException, BrokenFileException, IOException {
        userService.checkLogin();
        userService.checkRights();
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
        if (hotel.getName() == null || hotel.getCity() == null ||
                hotel.getCountry() == null || hotel.getStreet() == null)
            throw new BadRequestException("checkHotel failed: not all fields are filled");
    }
}