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

        try {
            findById(user.getId());
            throw new BadRequestException("User with id: " + user.getId() + " already exist");
        } catch (UserNotFoundException e) {
            System.out.println("User with id: " + user.getId() + " not found. Will be saved");
        }

        int index = 0;
        for (User us : users) {
            if (us == null) return users[index] = user;
            index++;
        }
        throw new InternalServerException("Not enough space to save user with id: " + user.getId());
    }


    public static User update(User user) throws Exception {
        if (user == null) throw new BadRequestException("Can`t update null user");
        findById(user.getId());

        int index = 0;
        for (User us : users) {
            if (us != null && us.getId() == user.getId() && !us.equals(user)) return users[index] = user;
            index++;
        }
        throw new InternalServerException("Unexpected error");
    }

    public static void delete(long id) throws Exception {
        findById(id);

        int index = 0;
        for (User us : users) {
            if (us != null && us.getId() == id) {
                users[index] = null;
                break;
            }
            index++;
        }
    }

    public static User findById(long id) throws UserNotFoundException {
        for (User user : users) {
            if (user != null && user.getId() == id) return user;
        }
        throw new UserNotFoundException("User with id: " + id + " not found");
    }
}