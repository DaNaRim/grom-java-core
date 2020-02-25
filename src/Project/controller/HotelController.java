package Project.controller;

import Project.exception.*;
import Project.model.Hotel;
import Project.service.HotelService;

import java.io.IOException;

public class HotelController {
    HotelService hotelService = new HotelService();

    public Hotel findHotelByName(String name)
            throws BadRequestException, NoAccessException, BrokenFileException, IOException {
        return hotelService.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city)
            throws BadRequestException, NoAccessException, BrokenFileException, IOException {
        return hotelService.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel)
            throws BrokenFileException, NotLogInException, NoAccessException, BadRequestException, IOException {
        return hotelService.addHotel(hotel);
    }

    public void deleteHotel(long hotelId)
            throws BrokenFileException, NotLogInException, NoAccessException, BadRequestException, IOException {
        hotelService.deleteHotel(hotelId);
    }
}