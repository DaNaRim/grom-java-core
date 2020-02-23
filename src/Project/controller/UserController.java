package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.model.User;
import Project.service.UserService;

import java.io.IOException;

public class UserController {
    private UserService userService = new UserService();

    public User registerUser(User user) {
        User user1 = null;
        try {
            user1 = userService.registerUser(user);
            System.out.println("registerUser successful: " + user1.getId());
        } catch (IOException | BrokenFileException e) {
            System.err.println("registerUser failed: " + e.getMessage());
        }
        return user1;
    }

    public void login(String userName, String password) {
        try {
            userService.login(userName, password);
            System.out.println("login successful");
        } catch (IOException | BrokenFileException | BadRequestException e) {
            System.err.println("login failed: " + e.getMessage());
        }
    }

    public void logout() {
        userService.logout();
        System.out.println("logout successful");
    }
}