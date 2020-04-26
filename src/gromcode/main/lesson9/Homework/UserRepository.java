package gromcode.main.lesson9.Homework;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public String[] getUserNames() {
        String[] namesArray = new String[countUsers()];

        int index = 0;
        for (User user : users) {
            if (user != null) {
                namesArray[index] = user.getName();
                index++;
            }
        }
        return namesArray;
    }

    public long[] getUserIds() {
        long[] idsArray = new long[countUsers()];
        int index = 0;

        for (User user : users) {
            if (user != null) {
                idsArray[index] = user.getId();
                index++;
            }
        }
        return idsArray;
    }

    public String getUserNameById(long id) {
        for (User user : users) {
            if (user != null && id == user.getId()) return user.getName();
        }
        return null;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user != null && name == user.getName()) return user;
        }
        return null;
    }

    public User findById(long id) {
        for (User user : users) {
            if (user != null && id == user.getId()) return user;
        }
        return null;
    }

    public User getUserBySessionId(String sessionId) {
        for (User user : users) {
            if (user != null && sessionId == user.getSessionId()) return user;
        }
        return null;
    }

    public User save(User user) {
        if (user == null || findById(user.getId()) != null || countUsers() == users.length) return null;

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
            if (us != null && us == findById(user.getId())) return users[index] = user;
            index++;
        }
        return null;
    }

    public void delete(long id) {
        int index = 0;
        for (User user : users) {
            if (user != null && user == findById(id)) {
                users[index] = null;
                break;
            }
            index++;
        }
    }

    private int countUsers() {
        int countUsers = 0;
        for (User user : users) {
            if (user != null) countUsers++;
        }
        return countUsers;
    }
}