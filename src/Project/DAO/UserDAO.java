package Project.DAO;

import Project.model.User;
import Project.model.UserType;

import java.util.LinkedList;
import java.util.UUID;

public class UserDAO extends MainDAO<User> {
    private String path = "testPath";

    public User registerUser(User user) {
        return addToFile(new User(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                user.getUserName(),
                user.getPassword(),
                user.getCountry(),
                user.getUserType()));
    }

    public void login(String userName, String password) {
        //TODO login user
    }

    public void logout() {
        //TODO logout user
    }

    public User findUserById(long id) throws Exception { //TODO Exception
        for (User user : getFromFile()) {
            if (user.getId() == id) return user;
        }
        throw new Exception("Missing user with id: " + id);
    }

    @Override
    LinkedList<User> getFromFile() {
        return super.getFromFile();
    }

    @Override
    User addToFile(User user) {
        return super.addToFile(user);
    }

    @Override
    void deleteFromFile(Long id) {
        super.deleteFromFile(id);
    }

    @Override
    User map(String line) throws Exception { //TODO Exception
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        return new User(Long.parseLong(fields[1]), fields[2], fields[3], fields[4], UserType.valueOf(fields[5]));
    }
}