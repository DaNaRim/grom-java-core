package Project.service;

import Project.DAO.UserDAO;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.model.User;

import java.io.IOException;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private static User loginUser = null;

    public static User getLoginUser() {
        return loginUser;
    }

    public User registerUser(User user) throws IOException, BrokenFileException {
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password) throws IOException, BrokenFileException, BadRequestException {
        for (User user : userDAO.getFromFile()) {
            if (user.getUserName().equals(userName)) {
                if (loginUser.getId().equals(user.getId()))
                    throw new BadRequestException("User: " + user.getId() + " already login");

                if (user.getPassword().equals(password))
                    loginUser = user;
                throw new BadRequestException("wrong user password: " + user.getId());
            }
        }
        throw new BadRequestException("wrong username or user not registered");
    }

    public void logout() {
        loginUser = null;
    }
}