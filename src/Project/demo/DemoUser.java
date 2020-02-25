package Project.demo;

import Project.controller.UserController;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.User;
import Project.model.UserType;

import java.io.IOException;

public class DemoUser {
    private static UserController userController = new UserController();

    public static void main(String[] args)
            throws IOException, BrokenFileException, NoAccessException, BadRequestException, NotLogInException {

        User user1 = new User("DaNaRim", "f5urhg%89aohfol347hgfv93", "Ukraine", UserType.ADMIN);

        userController.registerUser(user1);


//        System.out.println(userController.registerUser(user1));
//
//        userController.login("Oleg", "fs341");
//
//        userController.logout();
    }
}