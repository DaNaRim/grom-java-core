package Project.service;

import Project.DAO.UserDAO;
import Project.exception.*;
import Project.model.User;
import Project.model.UserType;

import java.io.IOException;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private static User loggedUser = null;

    public User registerUser(User user)
            throws IOException, BrokenFileException, NoAccessException, BadRequestException {
        checkUser(user);
        checkUserName(user);
        checkUserPassword(user);
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password)
            throws IOException, InternalServerException, BadRequestException, NoAccessException {
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
            throws IOException, InternalServerException, BadRequestException, NoAccessException {
        if (loggedUser != null) {
            if (loggedUser.getUserName().equals(userName))
                throw new BadRequestException("validateLogin failed: user already log in");
            throw new InternalServerException("validateLogin failed: another user is logged in now");
        }

        for (User user : userDAO.getFromFile()) {
            if (user.getUserName().equals(userName)) {
                return checkPassword(user, password);
            }
        }
        throw new BadRequestException("validateLogin failed: wrong username or user not registered");
    }

    private User checkPassword(User user, String password) throws BadRequestException {
        if (user.getPassword().equals(password))
            return user;
        throw new BadRequestException("validateLogin failed: wrong password");
    }

    private void checkUser(User user) throws BadRequestException {
        if (user.getUserName() == null || user.getPassword() == null ||
                user.getCountry() == null || user.getUserType() == null)
            throw new BadRequestException("checkUser failed: not all fields are filled");
    }

    private void checkUserName(User user)
            throws NoAccessException, BrokenFileException, IOException, BadRequestException {
        for (User user1 : userDAO.getFromFile()) {
            if (user1.getUserName().equals(user.getUserName()))
                throw new BadRequestException("checkUserName failed: username is already taken");
        }
    }

    private void checkUserPassword(User user) throws BadRequestException {
        if (user.getPassword().length() < 8)
            throw new BadRequestException("checkUserPassword failed: password must be at least 8 characters");
    }
}