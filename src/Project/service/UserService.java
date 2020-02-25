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

    public User registerUser(User user) throws IOException, BrokenFileException, NoAccessException {
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password)
            throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        loggedUser = validateLogin(userName, password);
    }

    public void logout() throws NotLogInException {
        checkLogin();
        loggedUser = null;
    }

    public void checkLogin() throws NotLogInException {
        if (loggedUser == null) throw new NotLogInException("checkLogin failed: user is not log in");
    }

    public void checkRights() throws NoAccessException {
        if (loggedUser.getUserType() != UserType.ADMIN)
            throw new NoAccessException("checkRights failed: user don`t have enough rights");
    }

    private User validateLogin(String userName, String password)
            throws IOException, BrokenFileException, BadRequestException, NoAccessException {
        for (User user : userDAO.getFromFile()) {
            if (user.getUserName().equals(userName)) return checkUser(user, password);
        }
        throw new BadRequestException("validateLogin failed: wrong username or user not registered");
    }

    private User checkUser(User user, String password) throws BadRequestException {
        if (loggedUser.getId().equals(user.getId()))
            throw new BadRequestException("checkUser failed: user already log in");
        if (user.getPassword().equals(password)) return user;
        throw new BadRequestException("checkUser failed: wrong password");
    }
}