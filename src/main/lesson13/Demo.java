package main.lesson13;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        User user = new User(1001, "Ann", "1w21212");

        userRepository.save(user);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        userRepository.save(user);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        //сохранение юзера +
        //сохранение того же юзера +
        //когда нет места в массиве +
        //когда сохраняем null +

        for (int i = 0; i < 12; i++) {
            User user1 = new User(i, "Ann", "1w21212");
            System.out.println(userRepository.save(user1));
        }
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        userRepository.save(null);

        //test update
        //обновление юзера +
        //когда нет юзера на обновление +
        //когда обновляем null +

        user = new User(1001, "Ann", "eretertert");
        userRepository.update(user);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        user = new User(9999, "Ann", "eretertert");
        userRepository.update(user);
        System.out.println(userRepository.update(user));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        System.out.println(userRepository.update(null));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        userRepository.delete(3);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));

        userRepository.save(user);
    }
}
