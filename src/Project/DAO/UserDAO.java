package Project.DAO;

import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.User;
import Project.model.UserType;

public class UserDAO extends DAOTools<User> {

    public UserDAO() {
        super(FileLocations.getUserFileLocation());
    }

    public User registerUser(User user) throws InternalServerException {
        return addObjectToDAO(user);
    }

    @Override
    public User map(String line) throws BrokenFileException {
        try {
            String[] fields = line.split(", ");
            if (fields.length > 5)
                throw new BrokenFileException("broken line: to many elements");

            return new User(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], UserType.valueOf(fields[4]));
        } catch (NumberFormatException | BrokenFileException e) {
            throw new BrokenFileException("map failed: " + e.getMessage());
        }
    }
}