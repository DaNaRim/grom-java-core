package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Hotel;
import Project.service.HotelService;

public class HotelController {
    private static HotelService hotelService = new HotelService();

    public Hotel findHotelByName(String name) throws BadRequestException, InternalServerException {
        return hotelService.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws BadRequestException, InternalServerException {
        return hotelService.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel)
            throws InternalServerException, NotLogInException, NoAccessException, BadRequestException {
        return hotelService.addHotel(hotel);
    }

    public void deleteHotel(long hotelId)
            throws InternalServerException, NotLogInException, NoAccessException, BadRequestException {
        hotelService.deleteHotel(hotelId);
    }
}