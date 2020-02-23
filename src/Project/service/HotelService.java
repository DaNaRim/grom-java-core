package Project.service;

import Project.DAO.HotelDAO;
import Project.exception.NoAccessException;
import Project.exception.NotRegisteredException;
import Project.model.Hotel;
import Project.model.UserType;

public class HotelService {
    HotelDAO hotelDAO = new HotelDAO();

    public Hotel findHotelByName(String name) throws Exception {
        return hotelDAO.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws Exception {
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) throws Exception {
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("You don`t have enough rights");

        return hotelDAO.addHotel(hotel);
    }

    public void deleteHotel(long hotelId) throws Exception {
        if (UserService.getLoginUser() == null)
            throw new NotRegisteredException("You are not log in");

        if (UserService.getLoginUser().getUserType() != UserType.ADMIN)
            throw new NoAccessException("You don`t have enough rights");

        hotelDAO.deleteHotel(hotelId);
    }
}