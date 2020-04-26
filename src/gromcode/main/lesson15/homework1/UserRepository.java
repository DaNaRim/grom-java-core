package gromcode.main.lesson15.homework1;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public User save(User user) {
        if (user == null || findUser(user) != null || countUsers() == users.length) return null;

        int index = 0;
        for (User us : users) {
            if (us == null) return users[index] = user;
            index++;
        }
        return null;
    }


    public User update(User user) {
        if (user == null || findById(user.getId()) == null) return null;

        int index = 0;
        for (User us : users) {
            if (us != null && us.getId() == user.getId() && !us.equals(user)) return users[index] = user;
            index++;
        }
        return null;
    }

    public void delete(long id) {
        if (findById(id) == null) return;

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
        if (user == null) return null;

        for (User us : users) {
            if (us != null && us.equals(user)) return user;
        }
        return null;
    }

    private User findById(long id) {
        for (User user : users) {
            if (user != null && user.getId() == id) return user;
        }
        return null;
    }

    private int countUsers() {
        int countUsers = 0;
        for (User user : users) {
            if (user != null) countUsers++;
        }
        return countUsers;
    }
}