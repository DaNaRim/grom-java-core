package main.lesson30.task1;

public class Demo {

    public static void main(String[] args) throws Exception {
        Bank euBank = new USBank(1222, "Sweden", Currency.EUR, 100, 1400, 4, 444343434);
        User user = new User(1001, "Denis", 12200, 40, "GMD", 1500, euBank);
        User user2 = new User(1001, "Denis", 12200, 40, "GMD", 1500, euBank);

        UkrainianBankSystem bankSystem = new UkrainianBankSystem();

        bankSystem.withdraw(user, 150);
        Thread.sleep(2000);
        bankSystem.withdraw(user, 10);
        System.out.println(bankSystem.getTransactions());

        bankSystem.fund(user, 150);
        Thread.sleep(2000);
        bankSystem.fund(user, 10);
        System.out.println(bankSystem.getTransactions());

        bankSystem.transferMoney(user2, user, 150);
        Thread.sleep(2000);
        bankSystem.transferMoney(user, user2, 150);
        System.out.println(bankSystem.getTransactions());

        bankSystem.paySalary(user);
        Thread.sleep(2000);
        bankSystem.paySalary(user2);
        System.out.println(bankSystem.getTransactions());
    }
}
