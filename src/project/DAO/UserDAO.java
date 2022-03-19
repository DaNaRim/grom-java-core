package project.DAO;

import project.exception.InternalServerException;
import project.exception.NotFoundException;
import project.model.User;
import project.model.UserType;

public class UserDAO extends DAO<User> {

    public UserDAO() {
        super(DaoUtil.USER_DAO_PATH);
    }

    public User getUserByUsername(String userName) throws InternalServerException, NotFoundException {
        for (User user : getAll()) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        throw new NotFoundException("getUserByUserName failed: can`t find user with username " + userName);
    }


    public boolean isUsernameUnique(String userName) throws InternalServerException {
        for (User user : getAll()) {
            if (user.getUserName().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected User map(String line) {
        String[] fields = line.split(", ");

        return new User(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], UserType.valueOf(fields[4]));
    }

}
