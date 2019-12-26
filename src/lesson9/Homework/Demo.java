package lesson9.Homework;

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

        userRepository.delete(1001);
        System.out.println(Arrays.deepToString(userRepository.getUsers()));
        System.out.println();

        nullUserRepository.delete(1001);
        System.out.println(Arrays.deepToString(nullUserRepository.getUsers()));
        System.out.println();

        //test "countUsers"

        //подсчет юзеров
        //когда user null
        //когда users null
/*
        System.out.println(notNullUserRepository.countUsers());
        System.out.println();

        System.out.println(userRepository.countUsers());
        System.out.println();

        System.out.println(nullUserRepository.countUsers());
*/

        //test "getUserNames"

        //получение имен юзеров
        //когда user null
        //когда users null

        System.out.println(Arrays.toString(notNullUserRepository.getUserNames()));
        System.out.println();

        System.out.println(Arrays.toString(userRepository.getUserNames()));
        System.out.println();

        System.out.println(Arrays.toString(nullUserRepository.getUserNames()));
        System.out.println();

        //test "getUserIds"

        //получение id юзеров
        //когда user null
        //когда users null

        System.out.println(Arrays.toString(notNullUserRepository.getUserIds()));
        System.out.println();

        System.out.println(Arrays.toString(userRepository.getUserIds()));
        System.out.println();

        System.out.println(Arrays.toString(nullUserRepository.getUserIds()));
        System.out.println();

        //test "getUserNameById"

        //получение юзера по id
        //если нет юзера с таким id
        //когда user null
        //когда users null

        System.out.println(notNullUserRepository.getUserNameById(1001));
        System.out.println();

        System.out.println(notNullUserRepository.getUserNameById(54671));
        System.out.println();

        System.out.println(userRepository.getUserNameById(1002));
        System.out.println();

        System.out.println(nullUserRepository.getUserNameById(34));
        System.out.println();

        //test "getUserByName"

        //нахождение юзера по имени
        //когда нет юзера с таким именем
        //когда ищем null
        //когда user null
        //когда users null

        System.out.println(notNullUserRepository.getUserByName("AAA"));
        System.out.println();

        System.out.println(notNullUserRepository.getUserByName("null"));
        System.out.println();

        System.out.println(notNullUserRepository.getUserByName(null));
        System.out.println();

        System.out.println(userRepository.getUserByName("AAB"));
        System.out.println();

        System.out.println(nullUserRepository.getUserByName("ad"));
        System.out.println();

        //test "findById"

        //нахождение юзера по id
        //когда нет юзера с таким id
        //когда user null
        //когда users null

        System.out.println(notNullUserRepository.findById(1001));
        System.out.println();

        System.out.println(notNullUserRepository.findById(347912));
        System.out.println();

        System.out.println(userRepository.findById(1002));
        System.out.println();

        System.out.println(nullUserRepository.findById(2352));
        System.out.println();

        //test "getUserBySessionId"

        //нахождение юзера по SessionId
        //когда нет юзера с таким SessionId
        //когда ищем null
        //когда user null
        //когда users null

        System.out.println(notNullUserRepository.getUserBySessionId("1w21212"));
        System.out.println();

        System.out.println(notNullUserRepository.getUserBySessionId("347912"));
        System.out.println();

        System.out.println(notNullUserRepository.getUserBySessionId(null));
        System.out.println();

        System.out.println(userRepository.getUserBySessionId("1fasff1"));
        System.out.println();

        System.out.println(nullUserRepository.getUserBySessionId("1w21212"));
        System.out.println();
    }
}