package gromcode.main.lesson30.task1;

import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class UkrainianBankSystem implements BankSystem {

    private Set<Transaction> transactions = new TreeSet<>();

    @Override
    public void withdraw(User user, int amount) {
        if (user == null || !checkWithdraw(user, amount)) return;
        user.setBalance(user.getBalance() - amount - amount * user.getBank().getCommission(amount));

        createAndSaveTransaction(new Date(), TransactionType.WITHDRAW, amount, "sdsdc");
    }

    @Override
    public void fund(User user, int amount) {
        if (user == null || !checkFund(user, amount)) return;
        user.setBalance(user.getBalance() + amount);

        createAndSaveTransaction(new Date(), TransactionType.FUNDING, amount, "sdsdc");
    }

    @Override
    public void transferMoney(User fromUser, User toUser, int amount) {
        if (fromUser == null || toUser == null || !checkWithdraw(fromUser, amount) || !checkFund(toUser, amount) ||
                fromUser.getBank().getCurrency() != toUser.getBank().getCurrency()) return;

        withdraw(fromUser, amount);
        fund(toUser, amount);

        createAndSaveTransaction(new Date(), TransactionType.TRANSFER, amount, "sdsdc");
    }

    @Override
    public void paySalary(User user) {
        if (user == null || user.getSalary() > user.getBank().getLimitOfFunding()) return;

        user.setBalance(user.getBalance() + user.getSalary());

        createAndSaveTransaction(new Date(), TransactionType.SALARY_INCOME, user.getSalary(), "sdsdc");
    }

    private boolean checkWithdraw(User user, int amount) {
        return checkWithdrawLimits(user, amount, user.getBank().getLimitOfWithdrawal()) &&
                checkWithdrawLimits(user, amount, user.getBalance());
    }

    private boolean checkFund(User user, int amount) {
        return checkFundLimits(user, amount, user.getBank().getLimitOfFunding());
    }

    private boolean checkWithdrawLimits(User user, int amount, double limit) {
        if (amount + user.getBank().getCommission(amount) > limit) {
            printWithdrawErrorMsq(amount, user);
            return false;
        }
        return true;
    }

    private boolean checkFundLimits(User user, int amount, double limit) {
        if (amount > limit) {
            printFundErrorMsq(amount, user);
            return false;
        }
        return true;
    }

    private void printWithdrawErrorMsq(int amount, User user) {
        System.err.println("Can't withdraw money " + amount + " from user " + user.toString());
    }

    private void printFundErrorMsq(int amount, User user) {
        System.err.println("Can't fund money " + amount + " to user " + user.toString());
    }

    private Transaction createAndSaveTransaction(Date dateCreated, TransactionType type, int amount, String descr) {
        Random random = new Random();
        Transaction tr = new Transaction(random.nextInt(), dateCreated, null, type, amount, descr);
        transactions.add(tr);
        return tr;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }
}