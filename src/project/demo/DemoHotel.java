package project.demo;

import project.controller.HotelController;
import project.controller.UserController;
import project.model.Hotel;

public class DemoHotel {

    private static final HotelController hotelController = new HotelController();
    private static final UserController userController = new UserController();

    public static void main(String[] args) throws Exception {

        userController.login("First", "SuperPassword");

        userController.login("Second", "SuperPassword2");

        Hotel hotel = new Hotel("HotelToDelete", "USA", "SeaCity", "TestStreet2");
        hotelController.addHotel(hotel);

        System.out.println(hotelController.findHotelByCity("SeaCity").toString());
        System.out.println(hotelController.findHotelByName("TestHotel").toString());

        hotelController.deleteHotel(4549416648655917001L);
    }

}
