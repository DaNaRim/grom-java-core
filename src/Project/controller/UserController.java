package Project.controller;

import Project.model.User;
import Project.service.UserService;

public class UserController {
    private UserService userService = new UserService();

    public User registerUser(User user) {
        return userService.registerUser(user);
    }

    public void login(String userName, String password) {
        userService.login(userName, password);
    }

    public void logout() {
        userService.logout();
    }
}