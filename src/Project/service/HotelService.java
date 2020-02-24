package Project.service;

import Project.DAO.HotelDAO;
import Project.model.Hotel;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();
    private UserService userService = new UserService();

    public Hotel findHotelByName(String name) throws Exception {
        return hotelDAO.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws Exception {
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) throws Exception {
        userService.checkLogin();
        userService.checkRights();

        return hotelDAO.addHotel(hotel);
    }

    public void deleteHotel(long hotelId) throws Exception {
        userService.checkLogin();
        userService.checkRights();

        hotelDAO.deleteHotel(hotelId);
    }
}