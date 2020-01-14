package lesson13;

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

        int n = 15;
        while (n > 0) {
            User user1 = new User(n, "Ann", "1w21212");
            System.out.println(userRepository.save(user1));
            n--;
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
    }
}
