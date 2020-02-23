package Project.service;

import Project.DAO.HotelDAO;
import Project.model.Hotel;

public class HotelService {
    HotelDAO hotelDAO = new HotelDAO();

    public Hotel findHotelByName(String name) {
        //TODO check business logic

        //hotelDAO.findHotelByName(name)
        return null;
    }

    public Hotel findHotelByCity(String city) {
        //TODO check business logic

        //hotelDAO.findHotelByCity(city)
        return null;
    }

    public Hotel addHotel(Hotel hotel) {
        //TODO check business logic

        return hotelDAO.addHotel(hotel);
    }

    public void deleteHotel(long hotelId) {
        //TODO check business logic

        hotelDAO.deleteHotel(hotelId);
    }
}