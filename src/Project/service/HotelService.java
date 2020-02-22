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
}