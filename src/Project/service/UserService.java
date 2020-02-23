package Project.service;

import Project.DAO.UserDAO;
import Project.exception.BadRequestException;
import Project.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private static User loginUser = null;

    public static User getLoginUser() {
        return loginUser;
    }

    public User registerUser(User user) throws Exception {
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {
        for (User user : userDAO.getFromFile()) {
            if (user.getUserName().equals(userName)) {
                if (loginUser.getId().equals(user.getId()))
                    throw new BadRequestException("You already login");

                if (user.getPassword().equals(password))
                    loginUser = user;
                throw new BadRequestException("wrong password");
            }
        }
        throw new BadRequestException("wrong username or you not registered");
    }

    public void logout() {
        loginUser = null;
    }
}