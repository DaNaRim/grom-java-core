package project.controller;

import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.exception.NoAccessException;
import project.model.Hotel;
import project.service.HotelService;

import java.util.LinkedList;

public class HotelController {
    private static HotelService hotelService = new HotelService();

    public LinkedList<Hotel> findHotelByName(String name) throws BadRequestException, InternalServerException {
        return hotelService.findHotelByName(name);
    }

    public LinkedList<Hotel> findHotelByCity(String city) throws BadRequestException, InternalServerException {
        return hotelService.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) throws InternalServerException, NoAccessException, BadRequestException {
        return hotelService.addHotel(hotel);
    }

    public void deleteHotel(long hotelId) throws InternalServerException, NoAccessException, BadRequestException {
        hotelService.deleteHotel(hotelId);
    }
}