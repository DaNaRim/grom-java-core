package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.User;
import Project.model.UserType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

public class UserDAO extends MainDAO<User> {

    public UserDAO() {
        super(FileLocations.getUserFileLocation());
    }

    public User registerUser(User user) throws IOException, BrokenFileException, NoAccessException {
        user.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        return addToFile(user);
    }

    @Override
    public User findById(long id) throws IOException, InternalServerException, NoAccessException {
        return super.findById(id);
    }

    @Override
    public LinkedList<User> getFromFile() throws IOException, BrokenFileException, NoAccessException {
        return super.getFromFile();
    }

    @Override
    public User addToFile(User user) throws IOException, BrokenFileException, NoAccessException {
        return super.addToFile(user);
    }

    @Override
    public void deleteFromFile(Long id) throws IOException, BrokenFileException, NoAccessException {
        super.deleteFromFile(id);
    }

    @Override
    public User map(String line) throws NumberFormatException, BrokenFileException {
        String[] fields = line.split(", ");
        if (fields.length > 5) throw new BrokenFileException("map failed: broken line");
        return new User(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], UserType.valueOf(fields[4]));
    }
}