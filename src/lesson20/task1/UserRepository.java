package lesson20.task1;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public User save(User user) {
        if (users == null || user == null || findUser(user) != null) {
            return null;
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

    private User findById(long id) {
        if (users == null) return null;

        for (User user : users)
            if (user != null && user.getId() == id) return user;
        return null;
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
