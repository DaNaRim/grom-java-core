package gromcode.main.lesson12;

public class UkrainianBankSystem implements BankSystem {

    @Override
    public void withdraw(User user, int amount) {
        if (user == null || !checkWithdraw(user, amount)) return;
        user.setBalance(user.getBalance() - amount - amount * user.getBank().getCommission(amount));
    }

    @Override
    public void fund(User user, int amount) {
        if (user == null || !checkFund(user, amount)) return;
        user.setBalance(user.getBalance() + amount);
    }

    @Override
    public void transferMoney(User fromUser, User toUser, int amount) {
        if (fromUser == null || toUser == null || !checkWithdraw(fromUser, amount) || !checkFund(toUser, amount) ||
                fromUser.getBank().getCurrency() != toUser.getBank().getCurrency()) return;

        withdraw(fromUser, amount);
        fund(toUser, amount);
    }

    @Override
    public void paySalary(User user) {
        if (user == null || user.getSalary() > user.getBank().getLimitOfFunding()) return;

        user.setBalance(user.getBalance() + user.getSalary());
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
}