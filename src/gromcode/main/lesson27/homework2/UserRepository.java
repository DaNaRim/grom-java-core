package gromcode.main.lesson27.homework2;

import gromcode.main.lesson27.homework2.exception.BadRequestException;
import gromcode.main.lesson27.homework2.exception.UserNotFoundException;

import java.util.ArrayList;

public class UserRepository {
    private static ArrayList<User> users;

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User findUser(User user) throws Exception {
        if (user == null)
            throw new BadRequestException("Can`t found null user");

        return findById(user.getId());
    }

    public static User save(User user) throws Exception {
        if (user == null)
            throw new BadRequestException("Can`t save null user");

        try {
            findById(user.getId());
            throw new BadRequestException("User with id: " + user.getId() + " already exist");
        } catch (UserNotFoundException e) {
            System.out.println("User with id: " + user.getId() + " not found. Will be saved");
        }

        users.add(user);
        return users.get(users.size() - 1);
    }


    public static User update(User user) throws Exception {
        if (user == null)
            throw new BadRequestException("Can`t update null user");

        findById(user.getId());

        int index = getUserIndex(user);
        users.set(index, user);
        return users.get(index);
    }

    public static void delete(long id) throws Exception {
        users.remove(findById(id));
    }

    public static User findById(long id) throws UserNotFoundException {
        for (User user : users)
            if (user != null && user.getId() == id) return user;

        throw new UserNotFoundException("User with id: " + id + " not found");
    }

    private static int getUserIndex(User user) throws Exception {
        int index = 0;
        for (User us : users) {
            if (us.getId() == user.getId() && !us.equals(user))
                return index;
            index++;
        }
        throw new BadRequestException("There is nothing to update in the user with id: " + user.getId());
    }
}
