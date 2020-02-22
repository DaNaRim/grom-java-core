package Project.model;

public class User {
    private Long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;

    public User(Long id, String userName, String password, String country, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
    }

    //TODO simplify
}