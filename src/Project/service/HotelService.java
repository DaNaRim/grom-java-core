package Project.service;

import Project.DAO.HotelDAO;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Hotel;

import java.io.IOException;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();
    private UserService userService = new UserService();

    public Hotel findHotelByName(String name) throws BadRequestException, IOException, BrokenFileException {
        return hotelDAO.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws BadRequestException, IOException, BrokenFileException {
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel)
            throws NotLogInException, NoAccessException, BadRequestException, BrokenFileException, IOException {

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
}