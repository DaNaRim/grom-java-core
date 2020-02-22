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
        if (users == null) return null;

        String[] namesArray = new String[countUsers()];

        int userIndex = 0;
        for (User specificUser : users) {
            if (specificUser != null) {
                namesArray[userIndex] = specificUser.getName();
                userIndex++;
            }
        }
        return namesArray;
    }

    public long[] getUserIds() {
        if (users == null) return null;

        long[] idsArray = new long[countUsers()];
        int userIndex = 0;

        for (User specificUser : users) {
            if (specificUser != null) {
                idsArray[userIndex] = specificUser.getId();
                userIndex++;
            }
        }
        return idsArray;
    }

    public String getUserNameById(long id) {
        if (users == null) return null;

        String name = null;

        for (User specificUser : users)
            if (specificUser != null && id == specificUser.getId()) name = specificUser.getName();
        return name;
    }

    public User getUserByName(String name) {
        if (users == null) return null;

        for (User specificUser : users)
            if (specificUser != null && name == specificUser.getName()) return specificUser;
        return null;
    }

    public User findById(long id) {
        if (users == null) return null;

        for (User user : users)
            if (user != null && id == user.getId()) return user;
        return null;
    }

    public User getUserBySessionId(String sessionId) {
        if (users == null) return null;

        for (User specificUser : users)
            if (specificUser != null && sessionId == specificUser.getSessionId()) return specificUser;
        return null;
    }

    public User save(User user) {
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


    public User update(User user) {
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

    public void delete(long id) {
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

    private int countUsers() {
        if (users == null) return 0;

        int countUsers = 0;
        for (User user : users) {
            if (user != null) countUsers++;
        }
        return countUsers;
    }
}


