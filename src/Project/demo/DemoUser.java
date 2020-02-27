package Project.demo;

import Project.controller.UserController;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.User;
import Project.model.UserType;

public class DemoUser {
    private static UserController userController = new UserController();

    public static void main(String[] args)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException {

        User user1 = new User("Kratos", "8800553535", "Ukraine");

        userController.registerUser(user1);

        userController.login("Oleg", "wf45a4w44f");

        userController.setUserType(7734607807666275015L, UserType.ADMIN);

        userController.logout();
    }
}