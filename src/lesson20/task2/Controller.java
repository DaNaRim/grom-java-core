package lesson20.task2;

public class Controller {
    private TransactionDAO transactionsDAO = new TransactionDAO();

    public Transaction save(Transaction transaction) throws Exception {
        return transactionsDAO.save(transaction);
    }

    public Transaction[] transactionList() {
        return transactionsDAO.transactionList();
    }

    public Transaction[] transactionList(String city) {
        return transactionsDAO.transactionList(city);
    }

    public Transaction[] transactionList(int amount) {
        return transactionsDAO.transactionList(amount);
    }
}
