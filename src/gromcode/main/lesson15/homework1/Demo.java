package gromcode.main.lesson15.homework1;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        User user1 = new User(1001, "AAA", "1w21212");
        User user2 = new User(1002, "AAB", "asfasf1");
        User user3 = new User(1003, "AAC", "1fasff1");
        User user4 = new User(1004, "AAD", "1wwqet1");
        User user5 = new User(1005, "ABA", "fwqewef");
        User user6 = new User(1001, "ABB", "1qwf212");

        User[] users = {user1, user2, null, user3, user4, null, null, null};
        User[] notNullUsers = {user1, user2, user3, user4};

        UserRepository userRepository = new UserRepository(users);
        UserRepository nullUserRepository = new UserRepository(null);
        UserRepository notNullUserRepository = new UserRepository(notNullUsers);

        //test "save"
        //сохранение юзера +
        //сохранение того же юзера +
        //когда нет места в массиве +
        //когда сохраняем null +
        //когда users null +

        System.out.println(userRepository.save(user5));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        System.out.println();

        System.out.println(userRepository.save(user6));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        System.out.println();

        System.out.println(notNullUserRepository.save(user5));
        System.out.println(Arrays.deepToString(notNullUserRepository.getUsers()));
        System.out.println();

        System.out.println(userRepository.save(null));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        System.out.println();

        System.out.println(nullUserRepository.save(user5));
        System.out.println(Arrays.deepToString(nullUserRepository.getUsers()));
        System.out.println();

        //test "update"
        //обновление юзера +
        //когда нет юзера на обновление +
        //когда обновляем null +
        //когда user null
        //когда users null +

        user1 = new User(1001, "AAA", "eretertert");
        System.out.println(notNullUserRepository.update(user1));
        System.out.println(Arrays.deepToString(notNullUserRepository.getUsers()));
        System.out.println();

        user2 = new User(9999, "Ann", "eretertert");
        System.out.println(notNullUserRepository.update(user2));
        System.out.println(Arrays.deepToString(notNullUserRepository.getUsers()));
        System.out.println();

        System.out.println(notNullUserRepository.update(null));
        System.out.println(Arrays.deepToString(notNullUserRepository.getUsers()));
        System.out.println();

        user1 = new User(1001, "AAA", "ddd");
        System.out.println(userRepository.update(user1));
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        System.out.println();

        System.out.println(nullUserRepository.update(user1));
        System.out.println(Arrays.deepToString(nullUserRepository.getUsers()));
        System.out.println();


        //test "delete"
        //удаление юзера +
        //когда нет юзера на удаление +
        //когда user null
        //когда users null +

        notNullUserRepository.delete(1001);
        System.out.println(Arrays.deepToString(notNullUserRepository.getUsers()));
        System.out.println();

        notNullUserRepository.delete(4576);
        System.out.println(Arrays.deepToString(notNullUserRepository.getUsers()));
        System.out.println();

        userRepository.delete(1003);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        System.out.println();

        nullUserRepository.delete(1001);
        System.out.println(Arrays.deepToString(nullUserRepository.getUsers()));
        System.out.println();


        //test findUser
        //нахождение юзера
        //нет подходящего юзера
        //когда user в масиве null
        //когда user null
        //когда users null +

        System.out.println(notNullUserRepository.findUser(user2));
        System.out.println();

        System.out.println(notNullUserRepository.findUser(user5));
        System.out.println();

        System.out.println(userRepository.findUser(user3));
        System.out.println();

        System.out.println(userRepository.findUser(null));
        System.out.println();

        System.out.println(nullUserRepository.findUser(user2));
        System.out.println();
    }
}
