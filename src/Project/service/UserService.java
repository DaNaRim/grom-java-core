package Project.service;

import Project.DAO.UserDAO;
import Project.exception.BadRequestException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.exception.NotLogInException;
import Project.model.User;
import Project.model.UserType;

public class UserService {
    private static UserDAO userDAO = new UserDAO();
    private static User loggedUser = null;

    public User registerUser(User user) throws InternalServerException, BadRequestException {
        validateUser(user);
        return userDAO.addObjectToDAO(user);
    }

    public void login(String userName, String password) throws InternalServerException, BadRequestException {
        validateLogin(userName);
        loggedUser = userDAO.logIn(userName, password);
    }

    public void logout() throws NotLogInException {
        checkLogIn();
        loggedUser = null;
    }

    public void setUserType(Long id, UserType userType)
            throws NoAccessException, BadRequestException, InternalServerException {
        checkAccess();
        User user = userDAO.findById(id);

        validateUserType(user, userType);
        try {
            user.setUserType(userType);
            userDAO.updateObjectInDAO(user);
        } catch (InternalServerException e) {
            throw new InternalServerException("setUserType failed: " + e.getMessage());
        }
    }

    public void checkLogIn() throws NotLogInException {
        if (loggedUser == null) throw new NotLogInException("checkLogIn failed: user is not log in");
    }

    public void checkAccess() throws NoAccessException {
        checkLogIn();
        if (loggedUser.getUserType() != UserType.ADMIN)
            throw new NoAccessException("checkAccess failed: user don`t have enough rights");
    }

    public void checkUserForOperation(Long id) throws NoAccessException {
        checkLogIn();
        if (!loggedUser.getId().equals(id))
            throw new NoAccessException("checkUserForOperation failed: user cannot do this operation in the name of " +
                    "another user");
    }

    private void validateLogin(String userName) throws BadRequestException {
        if (loggedUser != null) {
            if (loggedUser.getUserName().equals(userName))
                throw new BadRequestException("validateLogin failed: user already log in");
            throw new BadRequestException("validateLogin failed: another user is logged in now");
        }
    }

    private void validateUser(User user) throws BadRequestException, InternalServerException {
        if (user == null)
            throw new BadRequestException("validateUser failed: impossible to process null user");

        if (user.getUserName() == null || user.getPassword() == null || user.getCountry() == null)
            throw new BadRequestException("validateUser failed: not all fields are filled");

        userDAO.usernameCheckForUniqueness(user.getUserName());

        if (user.getPassword().length() < 8)
            throw new BadRequestException("validateUser failed: password must be at least 8 characters");
    }

    private void validateUserType(User user, UserType userType) throws BadRequestException {
        if (user.getUserType() == userType)
            throw new BadRequestException("validateUserType failed: user already has this type");
    }
}