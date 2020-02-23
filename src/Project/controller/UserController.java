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
            System.out.println("User: " + user1.getId() + " registered successfully");
        } catch (IOException | BrokenFileException e) {
            System.err.println("Cant register user: " + user.getUserName() + " : " + e.getMessage());
        }
        return user1;
    }

    public void login(String userName, String password) {
        try {
            userService.login(userName, password);
            System.out.println("User: " + userName + " login successfully");
        } catch (IOException | BrokenFileException | BadRequestException e) {
            System.err.println("Login user: " + userName + " failed: " + e.getMessage());
        }
    }

    public void logout() {
        userService.logout();
        System.out.println("logout was successful");
    }
}