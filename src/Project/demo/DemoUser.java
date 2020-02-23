package Project.demo;

import Project.controller.UserController;
import Project.model.User;
import Project.model.UserType;

public class DemoUser {
    private static UserController userController = new UserController();

    public static void main(String[] args) {
        User user1 = new User("Oleg", "fs341", "Ukraine", UserType.USER);

        System.out.println(userController.registerUser(user1));

        userController.login("Oleg", "fs341");

        userController.logout();
    }
}