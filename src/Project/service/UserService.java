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
        checkUser(user);
        userDAO.checkUserName(user);
        checkUserPassword(user);
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password) throws InternalServerException, BadRequestException {
        loggedUser = validateLogin(userName, password);
    }

    public void logout() throws NotLogInException {
        checkLogin();
        loggedUser = null;
    }

    public void setUserType(Long id, UserType userType)
            throws NoAccessException, BadRequestException, InternalServerException {
        checkAccess();
        User user = userDAO.findById(id);

        checkUserType(user, userType);
        try {
            user.setUserType(userType);
            userDAO.updateObjectInDAO(id, user);
        } catch (InternalServerException e) {
            throw new InternalServerException("setUserType failed: " + e.getMessage());
        }
    }

    public void checkLogin() throws NotLogInException {
        if (loggedUser == null) throw new NotLogInException("checkLogin failed: user is not log in");
    }

    public void checkAccess() throws NoAccessException {
        checkLogin();
        if (loggedUser.getUserType() != UserType.ADMIN)
            throw new NoAccessException("checkRights failed: user don`t have enough rights");
    }

    public void checkUserForOperation(Long id) throws NoAccessException {
        checkLogin();
        if (!loggedUser.getId().equals(id))
            throw new NoAccessException("you cannot do this operation in the name of another user");
    }

    private User validateLogin(String userName, String password) throws InternalServerException, BadRequestException {
        if (loggedUser != null) {
            if (loggedUser.getUserName().equals(userName))
                throw new BadRequestException("validateLogin failed: user already log in");
            throw new BadRequestException("validateLogin failed: another user is logged in now");
        }
        return userDAO.validateLogin(userName, password);
    }

    private void checkUser(User user) throws BadRequestException {
        if (user == null)
            throw new BadRequestException("checkUser failed: impossible to process null user");

        if (user.getUserName() == null || user.getPassword() == null || user.getCountry() == null)
            throw new BadRequestException("checkUser failed: not all fields are filled");
    }

    private void checkUserPassword(User user) throws BadRequestException {
        if (user.getPassword().length() < 8)
            throw new BadRequestException("checkUserPassword failed: password must be at least 8 characters");
    }

    private void checkUserType(User user, UserType userType) throws BadRequestException {
        if (user.getUserType() == userType)
            throw new BadRequestException("setUserType failed: user already has this type");
    }
}