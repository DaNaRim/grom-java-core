package lesson12;

public class Main {
    public static void main(String[] args) {
        Bank euBank1 = new EUBank(0001, "Sweden", Currency.EUR, 100, 1400, 4, 444343434);
        Bank euBank2 = new EUBank(0002, "Poland", Currency.USD, 101, 1000, 5, 45743578);

        Bank usBank1 = new USBank(0011, "France", Currency.EUR, 102, 1300, 4, 997425333);
        Bank usBank2 = new USBank(0012, "Spain", Currency.USD, 103, 1100, 5, 45321442);

        Bank chinaBank1 = new ChinaBank(0021, "China", Currency.EUR, 104, 700, 3, 485364);
        Bank chinaBank2 = new ChinaBank(0022, "USA", Currency.USD, 105, 800, 2, 453434242);

        User user1 = new User(1001, "Denis", 12200, 66, "GMD", 1540, euBank1);
        User user2 = new User(1002, "Roman", 200001, 209, "Samsung", 3500, euBank2);
        User user3 = new User(1011, "Ivan", 222, 2, "ASUS", 1400, usBank1);
        User user4 = new User(1012, "Dasha", 500, 87, "Philips", 1200, usBank2);
        User user5 = new User(1021, "Anna", 5000, 56, "FFF", 1500, chinaBank1);
        User user6 = new User(1022, "Oleg", 200000, 120, "PolComp", 3000, chinaBank2);

        BankSystem bankSystem = new UkrainianBankSystem();

        bankSystem.withdraw(user1, 150);
        bankSystem.withdraw(user2, 250);
        bankSystem.withdraw(user3, 750);
        bankSystem.withdraw(user4, 1000);
        bankSystem.withdraw(user5, 1200);
        bankSystem.withdraw(user6, 2400);

        bankSystem.fund(user1, 150);
        bankSystem.fund(user2, 250);
        bankSystem.fund(user3, 750);
        bankSystem.fund(user4, 1000);
        bankSystem.fund(user5, 1200);
        bankSystem.fund(user6, 2400);

        bankSystem.transferMoney(user1, user2, 150);
        bankSystem.transferMoney(user2, user3, 250);
        bankSystem.transferMoney(user3, user4, 750);
        bankSystem.transferMoney(user4, user5, 1000);
        bankSystem.transferMoney(user5, user6, 1200);
        bankSystem.transferMoney(user6, user1, 2400);

        bankSystem.paySalary(user1);
        bankSystem.paySalary(user2);
        bankSystem.paySalary(user3);
        bankSystem.paySalary(user4);
        bankSystem.paySalary(user5);
        bankSystem.paySalary(user6);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);
        System.out.println(user6);
    }
}
