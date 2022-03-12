package gromcode.main.lesson22.repository;

import gromcode.main.lesson22.repository.exception.BadRequestException;
import gromcode.main.lesson22.repository.exception.InternalServerException;
import gromcode.main.lesson22.repository.exception.UserNotFoundException;

public class UserRepository {

    private static User[] users = new User[10];

    public static User[] getUsers() {
        return users;
    }

    public static User save(User user) throws Exception {
        if (user == null) throw new BadRequestException("Can`t save null user");

        if (isExists(user.getId())) {
            throw new BadRequestException("User with id: " + user.getId() + " already exist");
        }
        if (isStorageFull()) {
            throw new InternalServerException("Not enough space to save user with id: " + user.getId());
        }

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) continue;
            users[i] = user;
            break;
        }
        return user;
    }


    public static User update(User user) throws Exception {
        if (user == null) throw new BadRequestException("Can`t update null user");
        if (!isExists(user.getId())) throw new BadRequestException("Can`t find user with id " + user.getId());

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || !users[i].equals(user)) continue;
            users[i] = user;
        }
        return user;
    }

    public static void delete(long id) throws Exception {
        if (!isExists(id)) throw new BadRequestException("Can`t find user with id " + id);

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || users[i].getId() != id) continue;
            users[i] = null;
            break;
        }
    }

    public static User findById(long id) throws UserNotFoundException {
        for (User user : users) {
            if (user != null && user.getId() == id) return user;
        }
        throw new UserNotFoundException("User with id: " + id + " not found");
    }

    private static boolean isExists(long id) {
        for (User user : users) {
            if (user != null && user.getId() == id) return true;
        }
        return false;
    }

    private static boolean isStorageFull() {
        for (User user : users) {
            if (user == null) return false;
        }
        return true;
    }
}
