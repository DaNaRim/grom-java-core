package main.lesson22.homework;

import main.lesson22.homework.exception.BadRequestException;
import main.lesson22.homework.exception.InternalServerException;
import main.lesson22.homework.exception.LimitExceeded;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {

    private static Transaction[] transactions = new Transaction[10];

    public static Transaction save(Transaction transaction) throws Exception {
        validate(transaction);

        int index = 0;
        for (Transaction tr : transactions) {
            if (tr == null) return transactions[index] = transaction;
            index++;
        }
        return null;
    }

    private static void validate(Transaction transaction) throws Exception {
        if (transaction.getAmount() > Utils.getLimitSimpleTransactionAmount()) {
            throw new LimitExceeded("Transaction limit exceed " + transaction.getId() + ". Can`t be saved");
        }

        int sum = transaction.getAmount();
        int count = 1;
        for (Transaction tr : getTransactionPerDay(transaction.getDateCreated())) {
            sum += tr.getAmount();
            count++;
        }

        if (sum > Utils.getLimitTransactionsPerDayAmount()) {
            throw new LimitExceeded("Transaction limit per day amount exceed " + transaction.getId() + ". Can`t be saved");
        }
        if (count > Utils.getLimitTransactionsPerDayCount()) {
            throw new LimitExceeded("Transaction limit per day count exceed " + transaction.getId() + ". Can`t be saved");
        }
        if (!checkCity(transaction)) {
            throw new BadRequestException("Forbidden City " + transaction.getId() + ". Can`t be saved ");
        }

        for (Transaction tr : transactions) {
            if (transaction.equals(tr)) {
                throw new BadRequestException("Transaction already exists " + transaction.getId() + ". Can`t be saved");
            }
        }

        count = 0;
        for (Transaction tr : transactions) {
            if (tr != null) count++;
        }
        if (count == transactions.length) {
            throw new InternalServerException("No transaction space available" + transaction.getId() + ". Can`t be saved");
        }
    }

    public static Transaction[] transactionList() {
        int countTransaction = 0;
        for (Transaction tr : transactions) {
            if (tr != null) countTransaction++;
        }

        Transaction[] result = new Transaction[countTransaction];

        int index = 0;
        for (Transaction tr : transactions) {
            if (tr == null) continue;
            result[index] = tr;
            index++;
        }
        return result;
    }

    public static Transaction[] transactionList(String city) {
        int countTransaction = 0;
        for (Transaction tr : transactions) {
            if (tr != null && tr.getCity().equals(city)) countTransaction++;
        }

        Transaction[] result = new Transaction[countTransaction];

        int index = 0;
        for (Transaction tr : transactions) {
            if (tr != null && tr.getCity().equals(city)) {
                result[index] = tr;
                index++;
            }
        }
        return result;
    }

    public static Transaction[] transactionList(int amount) {
        int countTransaction = 0;
        for (Transaction tr : transactions) {
            if (tr != null && tr.getAmount() == amount) countTransaction++;
        }

        Transaction[] result = new Transaction[countTransaction];

        int index = 0;
        for (Transaction tr : transactions) {
            if (tr != null && tr.getAmount() == amount) {
                result[index] = tr;
                index++;
            }
        }
        return result;
    }

    private static boolean checkCity(Transaction transaction) {
        for (String str : Utils.getCities()) {
            if (transaction.getCity().equals(str)) return true;
        }
        return false;
    }

    private static Transaction[] getTransactionPerDay(Date datOfCurTransaction) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datOfCurTransaction);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int count = 0;
        for (Transaction transaction : transactions) {
            if ((transaction != null)) {
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if (trMonth == month && trDay == day) count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for (Transaction transaction : transactions) {
            if ((transaction != null)) {
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if (trMonth == month && trDay == day) {
                    result[index] = transaction;
                    index++;
                }
            }
        }
        return result;
    }
}
