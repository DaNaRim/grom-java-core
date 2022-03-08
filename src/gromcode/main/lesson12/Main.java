package gromcode.main.lesson12;

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
        User user6 = new User(1022, "Oleg", 200000, 120, "PolComp", 300000, chinaBank2);

        BankSystem bankSystem = new UkrainianBankSystem();
/*
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
*/
        //test getLimitOfWithdrawal ChinaBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(chinaBank1.getLimitOfWithdrawal() == 150 ? "Correct" : "Wrong");
        System.out.println(chinaBank2.getLimitOfWithdrawal() == 100 ? "Correct" : "Wrong");
        System.out.println();

        //test getLimitOfFunding ChinaBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(chinaBank1.getLimitOfFunding() == 5000 ? "Correct" : "Wrong");
        System.out.println(chinaBank2.getLimitOfFunding() == 10000 ? "Correct" : "Wrong");
        System.out.println();

        //test getMonthlyRate ChinaBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(chinaBank1.getMonthlyRate() == 0 ? "Correct" : "Wrong");
        System.out.println(chinaBank2.getMonthlyRate() == 0.01 ? "Correct" : "Wrong");
        System.out.println();

        //test getCommission ChinaBank class
        //Якщо Currency = EUR, amount <= 1000
        //Якщо Currency = EUR, amount > 1000
        //Якщо Currency = USD, amount <= 1000
        //Якщо Currency = USD, amount > 1000

        System.out.println(chinaBank1.getCommission(200) == 0.1 ? "Correct" : "Wrong");
        System.out.println(chinaBank1.getCommission(44444) == 0.11 ? "Correct" : "Wrong");
        System.out.println(chinaBank2.getCommission(120) == 0.03 ? "Correct" : "Wrong");
        System.out.println(chinaBank2.getCommission(4444) == 0.1 ? "Correct" : "Wrong");
        System.out.println();

        //test getLimitOfWithdrawal EUBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(euBank1.getLimitOfWithdrawal() == 2200 ? "Correct" : "Wrong");
        System.out.println(euBank2.getLimitOfWithdrawal() == 2000 ? "Correct" : "Wrong");
        System.out.println();

        //test getLimitOfFunding EUBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(euBank1.getLimitOfFunding() == 20000 ? "Correct" : "Wrong");
        System.out.println(euBank2.getLimitOfFunding() == 10000 ? "Correct" : "Wrong");
        System.out.println();

        //test getMonthlyRate EUBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(euBank1.getMonthlyRate() == 0.01 ? "Correct" : "Wrong");
        System.out.println(euBank2.getMonthlyRate() == 0 ? "Correct" : "Wrong");
        System.out.println();

        //test getCommission EUBank class
        //Якщо Currency = EUR, amount <= 1000
        //Якщо Currency = EUR, amount > 1000
        //Якщо Currency = USD, amount <= 1000
        //Якщо Currency = USD, amount > 1000

        System.out.println(euBank1.getCommission(200) == 0.02 ? "Correct" : "Wrong");
        System.out.println(euBank1.getCommission(44444) == 0.04 ? "Correct" : "Wrong");
        System.out.println(euBank2.getCommission(120) == 0.05 ? "Correct" : "Wrong");
        System.out.println(euBank2.getCommission(4444) == 0.07 ? "Correct" : "Wrong");
        System.out.println();

        //test getLimitOfWithdrawal USBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(usBank1.getLimitOfWithdrawal() == 1200 ? "Correct" : "Wrong");
        System.out.println(usBank2.getLimitOfWithdrawal() == 1000 ? "Correct" : "Wrong");
        System.out.println();

        //test getLimitOfFunding USBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(usBank1.getLimitOfFunding() == 10000 ? "Correct" : "Wrong");
        System.out.println(usBank2.getLimitOfFunding() == Integer.MAX_VALUE ? "Correct" : "Wrong");
        System.out.println();

        //test getMonthlyRate USBank class
        //Якщо Currency = EUR
        //Якщо Currency = USD

        System.out.println(usBank1.getMonthlyRate() == 0.02 ? "Correct" : "Wrong");
        System.out.println(usBank2.getMonthlyRate() == 0.01 ? "Correct" : "Wrong");
        System.out.println();

        //test getCommission USBank class
        //Якщо Currency = EUR, amount <= 1000
        //Якщо Currency = EUR, amount > 1000
        //Якщо Currency = USD, amount <= 1000
        //Якщо Currency = USD, amount > 1000

        System.out.println(usBank1.getCommission(200) == 0.06 ? "Correct" : "Wrong");
        System.out.println(usBank1.getCommission(44444) == 0.08 ? "Correct" : "Wrong");
        System.out.println(usBank2.getCommission(120) == 0.05 ? "Correct" : "Wrong");
        System.out.println(usBank2.getCommission(4444) == 0.07 ? "Correct" : "Wrong");
        System.out.println();

        //test withdraw UkrainianBankSystem class
        //Зняття
        //Коли перевищена сума
        //Коли нема на балансі
        //Якщо user = null

        bankSystem.withdraw(user1, 1500);
        System.out.println(user1.getBalance());

        bankSystem.withdraw(user2, 2500);
        System.out.println(user2.getBalance());

        bankSystem.withdraw(user3, 300);
        System.out.println(user3.getBalance());

        bankSystem.withdraw(null, 1500);
        System.out.println();

        //test fund UkrainianBankSystem class
        //Поповнення
        //Коли перевищена сума
        //Якщо user = null

        bankSystem.fund(user1, 8000);
        System.out.println(user1.getBalance());

        bankSystem.fund(user2, 25000);
        System.out.println(user2.getBalance());

        bankSystem.fund(null, 1500);
        System.out.println();

        //test transferMoney UkrainianBankSystem class
        //Переведення
        //Коли перевищена сума зняття
        //Коли перевищена сума поповнення
        //Коли різні валюти
        //Якщо fromUser = null
        //Якщо toUser = null

        bankSystem.transferMoney(user1, user3, 800);
        System.out.println(user1.getBalance());
        System.out.println(user3.getBalance());
        System.out.println("---");

        bankSystem.transferMoney(user2, user6, 3000);
        System.out.println(user2.getBalance());
        System.out.println(user6.getBalance());
        System.out.println("---");

        bankSystem.transferMoney(user1, user5, 80000);
        System.out.println(user1.getBalance());
        System.out.println(user5.getBalance());
        System.out.println("---");

        bankSystem.transferMoney(user1, user2, 80);
        System.out.println(user1.getBalance());
        System.out.println(user2.getBalance());
        System.out.println("---");

        bankSystem.transferMoney(null, user2, 80);
        System.out.println(user2.getBalance());
        System.out.println("---");

        bankSystem.transferMoney(user1, null, 80);
        System.out.println(user1.getBalance());
        System.out.println();

        //test paySalary UkrainianBankSystem class
        //поповнення
        //зарплата быльша ніж можнаXD
        //Якщо user = null

        System.out.println(user1.getBalance());
        bankSystem.paySalary(user1);
        System.out.println(user1.getBalance());

        System.out.println("---");
        System.out.println(user6.getBalance());
        bankSystem.paySalary(user6);
        System.out.println(user6.getBalance());

        bankSystem.paySalary(null);
    }
}
