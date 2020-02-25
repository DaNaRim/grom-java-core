package Project.demo;

import Project.controller.HotelController;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Hotel;

import java.io.IOException;

public class DemoHotel {
    private static HotelController hotelController = new HotelController();

    public static void main(String[] args)
            throws BadRequestException, NoAccessException, BrokenFileException, IOException, NotLogInException {


        Hotel hotel = new Hotel(333L, "SuperHotel", "Ukraine", "TestCity", "TestStreet");

        hotelController.addHotel(hotel);

//        System.out.println(hotelController.findHotelByCity("city"));
//
//        System.out.println(hotelController.findHotelByName("name"));
//
//        Hotel hotel = new Hotel(555L, "Orange", "Ukraine", "test", "Rose");
//
//        System.out.println(hotelController.addHotel(hotel));
//
//        hotelController.deleteHotel(hotel.getId());
    }
}