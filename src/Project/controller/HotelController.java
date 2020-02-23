package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotRegisteredException;
import Project.model.Hotel;
import Project.service.HotelService;

import java.io.IOException;

public class HotelController {
    HotelService hotelService = new HotelService();

    public Hotel findHotelByName(String name) {
        Hotel hotel = null;
        try {
            hotel = hotelService.findHotelByName(name);
            System.out.println("Hotel find: " + hotel.getId());
        } catch (BadRequestException | IOException | BrokenFileException e) {
            System.err.println("Can`t find hotel by name " + name + " : " + e.getMessage());
        }
        return hotel;
    }

    public Hotel findHotelByCity(String city) {
        Hotel hotel = null;
        try {
            hotel = hotelService.findHotelByCity(city);
            System.out.println("Hotel find: " + hotel.getId());
        } catch (BadRequestException | IOException | BrokenFileException e) {
            System.err.println("Can`t find hotel in city " + city + " : " + e.getMessage());
        }
        return hotel;
    }

    public Hotel addHotel(Hotel hotel) {
        Hotel resHotel = null;
        try {
            resHotel = hotelService.addHotel(hotel);
            System.out.println("Hotel: " + resHotel.getId() + " added successfully");
        } catch (NotRegisteredException |
                BadRequestException |
                BrokenFileException |
                IOException |
                NoAccessException e) {
            System.err.println("Can`t add hotel: " + hotel.getName() + " : " + e.getMessage());
        }
        return resHotel;
    }

    public void deleteHotel(long hotelId) {
        try {
            hotelService.deleteHotel(hotelId);
            System.out.println("Hotel delete successfully");
        } catch (NotRegisteredException |
                NoAccessException |
                BadRequestException |
                BrokenFileException |
                IOException e) {
            System.err.println("Can`t delete hotel: " + hotelId + " : " + e.getMessage());
        }
    }
}