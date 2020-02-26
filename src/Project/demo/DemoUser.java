package Project.demo;

import Project.controller.UserController;
import Project.exception.*;
import Project.model.User;
import Project.model.UserType;

import java.io.IOException;

public class DemoUser {
    private static UserController userController = new UserController();


    public static void main(String[] args)
            throws IOException, InternalServerException, NoAccessException, BadRequestException, NotLogInException {

        User user1 = new User("Kratos", "8800553535", "Ukraine");

        userController.registerUser(user1);

        userController.login("Oleg", "wf45a4w44f");

        user1.setUserType(UserType.ADMIN);

        userController.logout();
    }
}