package project.service;

import project.DAO.UserDAO;
import project.exception.*;
import project.model.User;
import project.model.UserType;

public class UserService {

    private static final UserDAO userDAO = new UserDAO();
    private static User loggedUser = null;

    public User registerUser(User user) throws InternalServerException, BadRequestException {
        validateUser(user);
        return userDAO.save(user);
    }

    public void login(String username, String password) throws InternalServerException, BadRequestException {
        validateLoggedUser(username);
        loggedUser = validateLoginAndGetUser(username, password);
    }

    public void logout() throws NotLogInException {
        isSomeUserLogged();
        loggedUser = null;
    }

    public void setUserType(long id, UserType userType)
            throws NoAccessException, BadRequestException, InternalServerException, NotLogInException,
            NotFoundException {

        checkForAdminPermissions();
        User user = userDAO.findById(id);

        if (user.getUserType() == userType) {
            throw new BadRequestException("setUserType failed: user already has this type");
        }
        user.setUserType(userType);
        userDAO.update(user);
    }

    public void checkForAdminPermissions() throws NoAccessException, NotLogInException {
        isSomeUserLogged();
        if (loggedUser.getUserType() != UserType.ADMIN) {
            throw new NoAccessException("checkForAdminPermissions failed: user don`t have enough rights");
        }
    }

    public void isLoggedUser(Long id) throws NoAccessException, NotLogInException {
        isSomeUserLogged();
        if (!loggedUser.getId().equals(id)) {
            throw new NoAccessException(
                    "isLoggedUser failed: user cannot do this operation in the name of another user");
        }
    }

    private void isSomeUserLogged() throws NotLogInException {
        if (loggedUser == null) throw new NotLogInException("isSomeUserLogged failed: user is not log in");
    }

    private void validateLoggedUser(String userName) throws BadRequestException {
        if (loggedUser == null) return;

        if (loggedUser.getUserName().equals(userName)) {
            throw new BadRequestException("validateLogin failed: user already log in");
        }
        throw new BadRequestException("validateLogin failed: another user is logged in now");
    }

    private User validateLoginAndGetUser(String username, String password)
            throws BadRequestException, InternalServerException {

        if (username == null || password == null) {
            throw new BadRequestException("validateLoginAndGetUser failed: not all fields are filed");
        }

        User user;
        try {
            user = userDAO.getUserByUsername(username);
        } catch (NotFoundException e) {
            throw new BadRequestException("validateAndGetUser failed: wrong username");
        }
        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("validateAndGetUser failed: wrong password");
        }
        return user;
    }

    private void validateUser(User user) throws BadRequestException, InternalServerException {
        if (user == null) {
            throw new BadRequestException("validateUser failed: impossible to process null user");
        }
        if (user.getUserName() == null
                || user.getPassword() == null
                || user.getCountry() == null
                || user.getUserName().isEmpty()
                || user.getPassword().isEmpty()
                || user.getCountry().isEmpty()) {
            throw new BadRequestException("validateUser failed: not all fields are filled");
        }
        if (user.getUserName().isBlank()
                || user.getPassword().isBlank()
                || user.getCountry().isBlank()) {
            throw new BadRequestException("validateUser failed: fields must not contain spaces");
        }
        if (user.getUserName().contains(", ")
                || user.getPassword().contains(", ")
                || user.getCountry().contains(", ")) {
            throw new BadRequestException("validateUser failed: fields must not have ', '");
        }
        if (user.getPassword().length() < 8) {
            throw new BadRequestException("validateUser failed: password must be at least 8 characters");
        }
        if (!userDAO.isUsernameUnique(user.getUserName())) {
            throw new BadRequestException("validateUser failed: username is already taken");
        }
    }

}
