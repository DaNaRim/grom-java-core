package gromcode.main.lesson22.homework;

import java.util.Date;

public class Demo {

    public static void main(String[] args) throws Exception {
        Transaction tr1 = new Transaction(1234124, "Kiev", 30, "Test", TransactionType.INCOME, new Date());
        Transaction tr2 = new Transaction(112424, "Kiev", 60, "Test", TransactionType.INCOME, new Date());
        Transaction tr3 = new Transaction(123854624, "Kiev", 40, "Test", TransactionType.INCOME, new Date());
        Transaction tr4 = new Transaction(12234524, "Odessa", 40, "Test", TransactionType.INCOME, new Date());
        Transaction tr5 = new Transaction(12999924, "Test", 30, "Test", TransactionType.INCOME, new Date());
        Transaction tr6 = new Transaction(1234124, "Kiev", 30, "Test", TransactionType.INCOME, new Date());

        try {
            Controller.save(tr1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Controller.save(tr2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Controller.save(tr3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Controller.save(tr4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Controller.save(tr5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Controller.save(tr6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Controller.transactionList();
        Controller.transactionList("Kiev");
        Controller.transactionList(30);
    }
}
