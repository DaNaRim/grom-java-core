package main.lesson27.homework2;

import java.util.Objects;

public class User {

    private long id;
    private String name;
    private String sessionId;

    public User(long id, String name, String sessionId) {
        this.id = id;
        this.name = name;
        this.sessionId = sessionId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
