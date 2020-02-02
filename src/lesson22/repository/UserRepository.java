package lesson22.repository;

public class UserRepository {
    private static User[] users = new User[10];

    public static User[] getUsers() {
        return users;
    }

    public static User save(User user) {
        if (users == null || user == null || findById(user.getId()) != null) {
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

    public static User update(User user) {
        if (users == null || user == null || findById(user.getId()) == null) return null;

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

    public static void delete(long id) {
        if (users != null) {
            int index = 0;
            for (User us : users) {
                if (us != null && us == findById(id)) {
                    users[index] = null;
                    break;
                }
                index++;
            }
        }
    }

    public static User findById(long id) {
        if (users == null) return null;

        for (User user : users)
            if (user != null && id == user.getId()) return user;
        return null;
    }

    private static int countUsers() {
        if (users == null) return 0;

        int countUsers = 0;
        for (User user : users) {
            if (user != null) countUsers++;
        }
        return countUsers;
    }
}


