package project.DAO;

import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.model.User;
import project.model.UserType;

public class UserDAO extends DAO<User> {

    public UserDAO() {
        super(DaoUtil.USER_DAO_PATH);
    }

    //TODO: remove bad request exceptions

    //TODO move to Service
    public User logIn(String userName, String password) throws InternalServerException, BadRequestException {
        for (User user : getAll()) {
            if (user.getUserName().equals(userName)) {
                return checkPassword(user, password);
            }
        }
        throw new BadRequestException("logIn failed: wrong username or user not registered");
    }

    public void usernameCheckForUniqueness(String userName) throws InternalServerException, BadRequestException {
        for (User user1 : getAll()) {
            if (user1.getUserName().equals(userName)) {
                throw new BadRequestException("usernameCheckForUniqueness failed: username is already taken");
            }
        }
    }

    @Override
    protected User map(String line) {
        String[] fields = line.split(", ");

        return new User(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], UserType.valueOf(fields[4]));
    }

    //TODO move to Service
    private User checkPassword(User user, String password) throws BadRequestException {
        if (user.getPassword().equals(password)) return user;
        throw new BadRequestException("checkPassword failed: wrong password");
    }

}
