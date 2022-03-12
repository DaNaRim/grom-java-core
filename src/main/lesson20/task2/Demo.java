package main.lesson20.task2;

import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {
        Transaction transaction1 = new Transaction(1234124, "Kiev", 30, "Test", TransactionType.INCOME, new Date());
        Transaction transaction2 = new Transaction(112424, "Kiev", 60, "Test", TransactionType.INCOME, new Date());
        Transaction transaction3 = new Transaction(123854624, "Kiev", 40, "Test", TransactionType.INCOME, new Date());
        Transaction transaction4 = new Transaction(12234524, "Odessa", 40, "Test", TransactionType.INCOME, new Date());
        Transaction transaction5 = new Transaction(12999924, "Test", 30, "Test", TransactionType.INCOME, new Date());
        Transaction transaction6 = new Transaction(1234124, "Kiev", 30, "Test", TransactionType.INCOME, new Date());

        Controller controller = new Controller();

        try {
            controller.save(transaction1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            controller.save(transaction2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            controller.save(transaction3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            controller.save(transaction4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            controller.save(transaction5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            controller.save(transaction6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        controller.transactionList();
        controller.transactionList("Kiev");
        controller.transactionList(30);
    }
}
