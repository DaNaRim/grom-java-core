package gromcode.main.lesson24.firstExample;

public class User {
    private int age;

    public User(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }
}