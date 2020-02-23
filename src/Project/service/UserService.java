package Project.service;

import Project.DAO.UserDAO;
import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.User;
import Project.model.UserType;

import java.io.IOException;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private static User loggedUser = null;

    public User registerUser(User user) throws IOException, BrokenFileException {
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password) throws IOException, BrokenFileException, BadRequestException {
        for (User user : userDAO.getFromFile()) {
            if (user.getUserName().equals(userName)) {
                if (loggedUser.getId().equals(user.getId()))
                    throw new BadRequestException("User already log in");

                if (user.getPassword().equals(password))
                    loggedUser = user;
                throw new BadRequestException("Wrong password");
            }
        }
        throw new BadRequestException("Wrong username or user not registered");
    }

    public void logout() {
        loggedUser = null;
    }

    public void checkLogin() throws NotLogInException {
        if (loggedUser == null)
            throw new NotLogInException("User are not log in");
    }

    public void checkRights() throws NoAccessException {
        if (loggedUser.getUserType() != UserType.ADMIN)
            throw new NoAccessException("User don`t have enough rights");
    }
}