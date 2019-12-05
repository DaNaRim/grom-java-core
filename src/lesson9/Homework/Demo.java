package lesson9.Homework;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        User user1 = new User(13, "Jack", "8800");
        User user2 = new User(14, "Bob", "555");
        User user3 = new User(15, "Ivan", "3535");
        User user4 = null;
        User[] us = null;
      /*  us[0] = user1;
        us[1] = null;
        us[2] = user3;*/

        UserRepository userRepository = new UserRepository(us);

        System.out.println(userRepository.save(user4));


    }
}
