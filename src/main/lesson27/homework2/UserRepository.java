package main.lesson27.homework2;

import main.lesson22.repository.exception.InternalServerException;
import main.lesson27.homework2.exception.BadRequestException;
import main.lesson27.homework2.exception.UserNotFoundException;

import java.util.ArrayList;

public class UserRepository {

    private static final ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User findUser(User user) throws Exception {
        if (user == null) throw new BadRequestException("Can`t found null user");
        return findById(user.getId());
    }

    public static User save(User user) throws Exception {
        if (user == null) {
            throw new BadRequestException("Can`t save null user");
        }
        if (isExists(user.getId())) {
            throw new BadRequestException("User with id: " + user.getId() + " already exist");
        }
        if (isStorageFull()) {
            throw new InternalServerException("Not enough space to save user with id: " + user.getId());
        }
        users.add(user);
        return user;
    }

    public static User update(User user) throws Exception {
        if (user == null) throw new BadRequestException("Can`t update null user");
        if (!isExists(user.getId())) throw new BadRequestException("Can`t find user with id " + user.getId());

        int index = getUserIndex(user);
        users.set(index, user);
        return users.get(index);
    }

    public static void delete(long id) throws Exception {
        users.remove(findById(id));
    }

    public static User findById(long id) throws UserNotFoundException {
        for (User user : users) {
            if (user != null && user.getId() == id) return user;
        }
        throw new UserNotFoundException("User with id: " + id + " not found");
    }

    private static int getUserIndex(User user) {
        int result = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) result = i;
        }
        return result;
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
