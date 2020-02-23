package Project.DAO;

import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.User;
import Project.model.UserType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

public class UserDAO extends MainDAO<User> {

    public UserDAO() {
        super(FileLocations.getUserFileLocation());
    }

    public User registerUser(User user) throws IOException, BrokenFileException {
        return addToFile(new User(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                user.getUserName(),
                user.getPassword(),
                user.getCountry(),
                user.getUserType()));
    }

    public User findUserById(long id) throws InternalServerException, IOException {
        for (User user : getFromFile()) {
            if (user.getId() == id) return user;
        }
        throw new InternalServerException("Missing user with id: " + id);
    }

    @Override
    public LinkedList<User> getFromFile() throws IOException, BrokenFileException {
        return super.getFromFile();
    }

    @Override
    public User addToFile(User user) throws IOException, BrokenFileException {
        return super.addToFile(user);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException {
        super.deleteFromFile(id);
    }

    @Override
    public User map(String line) throws NumberFormatException {
        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        return new User(Long.parseLong(fields[1]), fields[2], fields[3], fields[4], UserType.valueOf(fields[5]));
    }
}