package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.User;
import Project.model.UserType;

public class UserDAO extends DAOTools<User> {

    public UserDAO() {
        super(FileLocations.getUserFileLocation());
    }

    @Override
    public User map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 5)
                throw new BrokenFileException("to many elements");

            return new User(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], UserType.valueOf(fields[4]));
        } catch (NumberFormatException | BrokenFileException e) {
            throw new BrokenFileException(e.getMessage());
        }
    }

    public User logIn(String userName, String password) throws InternalServerException, BadRequestException {
        for (User user : getObjectsFromDAO()) {
            if (user.getUserName().equals(userName)) {
                return checkPassword(user, password);
            }
        }
        throw new BadRequestException("logIn failed: wrong username or user not registered");
    }

    public void usernameCheckForUniqueness(String userName) throws InternalServerException, BadRequestException {
        for (User user1 : getObjectsFromDAO()) {
            if (user1.getUserName().equals(userName))
                throw new BadRequestException("usernameCheckForUniqueness failed: username is already taken");
        }
    }

    private User checkPassword(User user, String password) throws BadRequestException {
        if (user.getPassword().equals(password))
            return user;
        throw new BadRequestException("checkPassword failed: wrong password");
    }
}