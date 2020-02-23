package Project.service;

import Project.DAO.HotelDAO;
import Project.model.Hotel;

public class HotelService {
    HotelDAO hotelDAO = new HotelDAO();

    public Hotel findHotelByName(String name) {
        //TODO check business logic

        return hotelDAO.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) {
        //TODO check business logic

        return hotelDAO.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) {
        //TODO check business logic

        return hotelDAO.addHotel(hotel);
    }

    public Hotel deleteHotel(long hotelId) {
        //TODO check business logic

        return hotelDAO.deleteHotel(hotelId);
    }
}