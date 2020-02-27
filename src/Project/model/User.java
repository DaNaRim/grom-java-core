package Project.model;

import Project.DAO.UserDAO;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.service.UserService;

public class User implements MainModel {
    private Long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType = UserType.USER;

    private static UserService userService = new UserService();
    private static UserDAO userDAO = new UserDAO();

    public User(String userName, String password, String country) {
        this.userName = userName;
        this.password = password;
        this.country = country;
    }

    public User(Long id, String userName, String password, String country, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return id + ", " + userName + ", " + password + ", " + country + ", " + userType;
    }
}