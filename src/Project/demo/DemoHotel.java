package Project.demo;

import Project.controller.HotelController;
import Project.model.Hotel;

public class DemoHotel {
    private static HotelController hotelController = new HotelController();

    public static void main(String[] args) {
        System.out.println(hotelController.findHotelByCity("city"));

        System.out.println(hotelController.findHotelByName("name"));

        Hotel hotel = new Hotel("Orange", "Ukraine", "test", "Rose");

        System.out.println(hotelController.addHotel(hotel));

        hotelController.deleteHotel(hotel.getId());
    }
}