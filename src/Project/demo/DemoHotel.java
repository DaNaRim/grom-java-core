package Project.demo;

import Project.controller.HotelController;
import Project.controller.UserController;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.Hotel;

public class DemoHotel {
    private static HotelController hotelController = new HotelController();
    private static UserController userController = new UserController();

    public static void main(String[] args)
            throws BadRequestException, NoAccessException, InternalServerException, NotLogInException {

        userController.login("Oleg", "wf45a4w44f");

        userController.login("DaNaRim", "f5urhg%89aohfol347hgfv93");


        Hotel hotel = new Hotel("TestHotel", "Ukraine", "TestCity", "TestStreet2");

        hotelController.addHotel(hotel);

        System.out.println(hotelController.findHotelByCity("TestCity").toString());

        System.out.println(hotelController.findHotelByName("TestHotel").toString());

        hotelController.deleteHotel(2982028930051293564L);
    }
}