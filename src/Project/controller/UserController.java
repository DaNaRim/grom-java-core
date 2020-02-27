package Project.controller;

import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.User;
import Project.model.UserType;
import Project.service.UserService;

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
            throws NotLogInException, InternalServerException, NoAccessException, BadRequestException {
        userService.setUserType(id, userType);
    }
}