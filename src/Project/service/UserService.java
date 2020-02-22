package Project.service;

import Project.DAO.UserDAO;
import Project.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User registerUser(User user) {
        //TODO check business logic

        return userDAO.registerUser(user); //another user
    }

    public void login(String userName, String password) {
        //TODO check business logic

        userDAO.login(userName, password);
    }

    public void logout() {
        //TODO check business logic

        userDAO.logout();
    }
}