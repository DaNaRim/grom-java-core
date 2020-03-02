package Project.demo;

import Project.controller.UserController;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.User;
import Project.model.UserType;

public class DemoUser {
    private static UserController userController = new UserController();

    public static void main(String[] args)
            throws InternalServerException, NoAccessException, BadRequestException {

        User user1 = new User("Nikita", "SuperPassword", "Sweden");

        userController.registerUser(user1);

        userController.login("Nikita", "SuperPassword");

        userController.setUserType(7734607807666275015L, UserType.ADMIN);

        userController.logout();
    }
}