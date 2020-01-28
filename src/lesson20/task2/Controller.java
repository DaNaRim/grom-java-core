package lesson20.task2;

import lesson20.task2.exception.LimitExceeded;

public class Controller {
    private TransactionDAO transactionsDAO = new TransactionDAO();

    public Transaction save(Transaction transaction) throws LimitExceeded {
        return transactionsDAO.save(transaction);
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

}
