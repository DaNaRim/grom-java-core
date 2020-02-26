package Project.controller;

import Project.exception.*;
import Project.model.User;
import Project.service.UserService;

import java.io.IOException;

public class UserController {
    private static UserService userService = new UserService();

    public User registerUser(User user)
            throws NoAccessException, BrokenFileException, IOException, BadRequestException {
        return userService.registerUser(user);
    }

    public void login(String userName, String password)
            throws BadRequestException, NoAccessException, InternalServerException, IOException {
        userService.login(userName, password);
    }

    public void logout() throws NotLogInException {
        userService.logout();
    }
}