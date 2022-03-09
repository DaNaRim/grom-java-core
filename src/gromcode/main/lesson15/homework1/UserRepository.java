package gromcode.main.lesson15.homework1;

public class UserRepository {

    private User[] users;

    public UserRepository(User[] users) {
        if (users == null) {
            this.users = new User[10];
        } else {
            this.users = users;
        }
    }

    public User[] getUsers() {
        return users;
    }

    public User save(User user) {
        if (user == null
                || findUser(user) != null
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
            if (users[i] == null || !users[i].equals(user)) continue;
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

    private boolean hasNullSell() {
        for (User user : users) {
            if (user == null) return true;
        }
        return false;
    }
}
