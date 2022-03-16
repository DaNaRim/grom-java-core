package project.demo;

import project.controller.HotelController;
import project.controller.UserController;
import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.exception.NoAccessException;
import project.model.Hotel;

public class DemoHotel {
    private static HotelController hotelController = new HotelController();
    private static UserController userController = new UserController();

    public static void main(String[] args)
            throws BadRequestException, NoAccessException, InternalServerException {

        userController.login("Oleg", "wf45a4w44f");

        userController.login("DaNaRim", "f5urhg%89aohfol347hgfv93");

        Hotel hotel = new Hotel("TestHotel", "Ukraine", "TestCity", "TestStreet2");

        hotelController.addHotel(hotel);

        System.out.println(hotelController.findHotelByCity("TestCity").toString());

        System.out.println(hotelController.findHotelByName("TestHotel").toString());

        hotelController.deleteHotel(7774759593198306259L);
    }
}