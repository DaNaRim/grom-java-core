package project.controller;

import project.exception.*;
import project.model.User;
import project.model.UserType;
import project.service.UserService;

public class UserController {
    private static UserService userService = new UserService();

    public User registerUser(User user) throws InternalServerException, BadRequestException {
        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws BadRequestException, InternalServerException {
        userService.login(userName, password);
    }

    public void logout() throws NotLogInException {
        userService.logout();
    }

    public void setUserType(Long id, UserType userType)
            throws InternalServerException, NoAccessException, BadRequestException, NotLogInException,
            NotFoundException {
        userService.setUserType(id, userType);
    }
}