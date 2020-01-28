package lesson20.task2;

import lesson20.task2.exception.LimitExceeded;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {
    private Transaction[] transactions = new Transaction[10];
    private Utils utils = new Utils();

    public Transaction save(Transaction transaction) throws LimitExceeded {
//        сумма транзакции больше указаного лимита +
//        сумма транзакции за день больше дневного лимита +
//        количество транзакций за день больше указаного лимита +
//        если горд оплати (совершения транзакции) не разрешен BadRequestException
//        не хватило места InternalServerException



        //TODO

        return transactions[4];
    }

    private void validate(Transaction transaction) throws LimitExceeded {
        if (transaction.getAmount() > utils.getLimitTransactionAmount())
            throw new LimitExceeded("Transaction limit exceed " + transaction.getId() + ". Can`t be saved ");

        int sum = 0;
        int count = 0;
        for (Transaction tr : getTransactionPerDay(transaction.getDateCreated())) {
            sum += tr.getAmount();
            count++;
        }

        if (sum > utils.getLimitTransactionPerDayAmount()) {
            throw new LimitExceeded("Transaction limit per day amount exceed " + transaction.getId() + ". Can`t be saved ");
        }

        if (count > utils.getLimitTransactionPerDayCount()) {
            throw new LimitExceeded("Transaction limit per day count exceed " + transaction.getId() + ". Can`t be saved ");
        }

        //TODO
    }

    Transaction[] transactionList() {

        return null;
    }

    Transaction[] transaction(String city) {

        return null;
    }

    Transaction[] transactionList(int amount) {

        return null;
    }

    private Transaction[] getTransactionPerDay(Date datOfCurTransaction) {
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

                if (trMonth == month && trDay == day)
                    count++;
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
