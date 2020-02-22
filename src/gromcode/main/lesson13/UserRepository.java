package gromcode.main.lesson13;

public class UserRepository {
    private User[] users = new User[10];

    public User[] getUsers() {
        return users;
    }

    public User getFindById(long id) {
        return findById(id);
    }

    public User save(User user) {
        if (user == null || findById(user.getId()) != null) {
            return null;
        }

        int countPlaced = 0;
        for (User us : users) {
            if (us != null)
                countPlaced++;
        }

        if (countPlaced > 9)
            return null;

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
        if (user == null || findById(user.getId()) == null)
            return null;

        int index = 0;
        for (User us : users) {
            if (us != null && us.getId() == findById(user.getId()).getId()) {
                users[index] = user;
                break;
            }
            index++;
        }
        return user;
    }

    public void delete(long id) {
        int index = 0;
        for (User us : users) {
            if (us != null && us == findById(id)) {
                users[index] = null;
                break;
            }
            index++;
        }
    }

    private User findById(long id) {
        for (User user : users) {
            if (user != null && id == user.getId())
                return user;
        }
        return null;
    }
}


