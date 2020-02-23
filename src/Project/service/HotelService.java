package Project.service;

import Project.DAO.HotelDAO;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotRegisteredException;
import Project.model.Hotel;
import Project.model.UserType;

import java.io.IOException;

public class HotelService {
    HotelDAO hotelDAO = new HotelDAO();

    public Hotel findHotelByName(String name) throws BadRequestException, IOException, BrokenFileException {
        return hotelDAO.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws BadRequestException, IOException, BrokenFileException {
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel)
            throws NotRegisteredException, NoAccessException, BadRequestException, BrokenFileException, IOException {

        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("User are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("User don`t have enough rights");

        return hotelDAO.addHotel(hotel);
    }

    public void deleteHotel(long hotelId)
            throws NotRegisteredException, NoAccessException, BadRequestException, BrokenFileException, IOException {
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("User are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("User don`t have enough rights");

        hotelDAO.deleteHotel(hotelId);
    }
}