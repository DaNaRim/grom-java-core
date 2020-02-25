package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.User;
import Project.service.UserService;

import java.io.IOException;

public class UserController {
    private UserService userService = new UserService();

    public User registerUser(User user)
            throws NoAccessException, BrokenFileException, IOException, BadRequestException {
        return userService.registerUser(user);
    }

    public void login(String userName, String password)
            throws BadRequestException, NoAccessException, BrokenFileException, IOException {
        userService.login(userName, password);
    }

    public void logout() throws NotLogInException {
        userService.logout();
    }
}