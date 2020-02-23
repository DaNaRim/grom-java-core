package Project.controller;

import Project.model.Hotel;
import Project.service.HotelService;

public class HotelController {
    HotelService hotelService = new HotelService();

    public Hotel findHotelByName(String name) {
        return hotelService.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) {
        return hotelService.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    public Hotel deleteHotel(long hotelId) {
        return hotelService.deleteHotel(hotelId);
    }
}