package main.lesson13;

public class UserRepository {

    private User[] users = new User[10];

    public User[] getUsers() {
        return users;
    }

    public User save(User user) {
        if (user == null
                || findById(user.getId()) != null
                || !hasNullSell()) return null;

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) continue;
            return users[i] = user;
        }
        return null;
    }

    public User update(User user) {
        if (user == null || findById(user.getId()) == null) return null;

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || users[i].getId() != user.getId()) continue;
            return users[i] = user;
        }
        return null;
    }

    public void delete(long id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null || users[i].getId() != id) continue;
            users[i] = null;
            break;
        }
    }

    private User findById(long id) {
        for (User user : users) {
            if (user != null && user.getId() == id) return user;
        }
        return null;
    }

    private boolean hasNullSell() {
        for (User user : users) {
            if (user == null) return true;
        }
        return false;
    }
}
