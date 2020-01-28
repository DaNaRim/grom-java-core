package lesson20.task1;

import lesson20.task1.exception.BadRequestException;
import lesson20.task1.exception.UserNotFoundException;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public User save(User user) throws Exception{
//        if (users == null ) return null;

        if (user == null)
            throw new BadRequestException("Can`t save null user");

        try {
            findById(user.getId());
            throw new BadRequestException("User with id: " + user.getId() + " already exist");
        } catch (UserNotFoundException e) {
            System.out.println("User with id: " + user.getId() + " not found. Will be saved");
        }

        if (findUser(user) != null){
            throw new BadRequestException("User with id: " + user.getId() + " already exist");
        }

        if (countUsers() == users.length) return null;

        int index = 0;
        for (User us : users) {
            if (us == null) {
                users[index] = user;
                break;
            }
            index++;
        }
        return user;
    }


    public User update(User user) {
        if (users == null || user == null || findById(user.getId()) == null) return null;

        int index = 0;
        for (User us : users) {
            if (us != null && us.getId() == user.getId() && !us.equals(user)) {
                users[index] = user;
                break;
            }
            index++;
        }
        return user;
    }

    public void delete(long id) {
        if (users == null || findById(id) == null) return;

        int index = 0;
        for (User us : users) {
            if (us != null && us.getId() == id) {
                users[index] = null;
                break;
            }
            index++;
        }
    }

    public User findUser(User user) {
        if (users == null || user == null) return null;

        for (User us : users)
            if (us != null && us.equals(user)) return user;
        return null;
    }

    public User findById(long id) throws UserNotFoundException {
//        if (users == null) return null;

        for (User user : users)
            if (user != null && user.getId() == id) return user;

        throw new UserNotFoundException("User with id: " + id + " not found");
    }

    private int countUsers() {
        if (users == null) return 0;

        int countUsers = 0;
        for (User user : users) {
            if (user != null) countUsers++;
        }
        return countUsers;
    }
}
