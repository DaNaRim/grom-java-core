package project.demo;

import project.controller.UserController;
import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.exception.NoAccessException;
import project.exception.NotLogInException;
import project.model.User;
import project.model.UserType;

public class DemoUser {
    private static UserController userController = new UserController();

    public static void main(String[] args)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException {

        User user1 = new User("h h, h", "SuperPassword", "Sweden");

        userController.registerUser(user1);

        userController.login("Nikita", "SuperPassword");

        userController.logout();

        userController.login("DaNaRim", "f5urhg%89aohfol347hgfv93");

        userController.setUserType(3916769799713539086L, UserType.USER);
    }
}