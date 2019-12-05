package lesson9.Homework;

public class UserRepository {
    private User[] users;

    public UserRepository(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    private int countUsers() {
        int countUsers = 0;
        for (User user : users) {
            if (user != null)
                countUsers++;
        }
        return countUsers;
    }

    public String[] getUserNames() {
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
        String name = null;

        for (User specificUser : users) {
            if (specificUser != null && id == specificUser.getId()) {
                name = specificUser.getName();
            }
        }
        return name;
    }

    public User getUserByName(String name) {
        for (User specificUser : users) {
            if (specificUser != null && name == specificUser.getName())
                return specificUser;
        }
        return null;
    }

    public User findById(long id) {
        for (User specificUser : users) {
            if (specificUser != null && id == specificUser.getId())
                return specificUser;
        }
        return null;
    }

    public User getUserBySessionId(String sessionId) {
        for (User specificUser : users) {
            if (specificUser != null && sessionId == specificUser.getSessionId())
                return specificUser;
        }
        return null;
    }

    public User save(User user) {
        if (user == null || findById(user.getId()) != null ||
                countUsers() == users.length) {
            return null;
        }
        int index = 0;
        for (User specificUser : users) {
            if (specificUser == null) {
                users[index] = user;
                return user;
            }
            index++;
        }
        return null;
    }
    /*  for (User specificUser : users) {
            if (specificUser == null) {
                specificUser = user;
                return user;
            }
        }
    */


    public User update(User user) {
        if (findById(user.getId()) == null)
            return null;
        int index = 0;
        for (User specificUser : users) {
            if (specificUser.getId() == user.getId()) {
                users[index] = user;
                return users[index];
            }
            index++;
        }
        return null;
    }

    public void delete(long id) {
        if (findById(id) != null) {
            int index = 0;
            for (User specificUser : users) {
                if (specificUser != null && specificUser == findById(id))
                    users[index] = null;
                index++;
            }
        }
    }

}


