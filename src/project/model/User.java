package project.model;

public class User extends BaseModel implements Comparable<User> {

    private Long id;
    private final String userName;
    private final String password;
    private final String country;
    private UserType userType = UserType.USER;

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

    @Override
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

    @Override
    public int compareTo(User user) {
        if (this.userType != user.getUserType()) {
            return user.getUserType() == UserType.ADMIN ? 1 : -1;
        }
        if (this.country.compareTo(user.getCountry()) != 0) {
            return this.country.compareTo(user.getCountry());
        }
        return this.userName.compareTo(user.getUserName());
    }

}
