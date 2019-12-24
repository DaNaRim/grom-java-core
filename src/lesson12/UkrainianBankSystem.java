package lesson12;

public class UkrainianBankSystem implements BankSystem {

    @Override
    public void withdraw(User user, int amount) {
        if (!checkOperation(user, amount, "withdraw"))
            return;
        user.setBalance(user.getBalance() - amount - amount * user.getBank().getCommission(amount));
    }

    @Override
    public void fund(User user, int amount) {
        if (!checkOperation(user, amount, "fund"))
            return;
        user.setBalance(user.getBalance() + amount - amount * user.getBank().getCommission(amount));
    }

    @Override
    public void transferMoney(User fromUser, User toUser, int amount) {
        if (!checkOperation(fromUser, amount, "withdraw") || !checkOperation(fromUser, amount, "fund"))
            return;
        fromUser.setBalance(fromUser.getBalance() - amount - amount * fromUser.getBank().getCommission(amount));
        toUser.setBalance(toUser.getBalance() + amount);
    }

    @Override
    public void paySalary(User user) {
        if (user.getSalary() > user.getBank().getLimitOfFunding())
            return;
        user.setBalance(user.getBalance() + user.getSalary());
    }

    private boolean checkOperation(User user, int amount, String operation) {
        if (operation == "withdraw") {
            return checkOperationLimits(user, amount, user.getBank().getLimitOfWithdrawal(), "withdraw") &&
                    checkOperationLimits(user, amount, user.getBalance(), "withdraw");
        } else //if (operation == "fund")
            return checkOperationLimits(user, amount, user.getBank().getLimitOfFunding(), "fund");
    }

    private boolean checkOperationLimits(User user, int amount, double limit, String operation) {
        if (operation == "withdraw" && amount + user.getBank().getCommission(amount) > limit) {
            printErrorMsq(amount, user, "withdraw");
            return false;
        } else if (operation == "fund" && amount > limit) {
            printErrorMsq(amount, user, "fund");
            return false;
        }
        return true;
    }

    private void printErrorMsq(int amount, User user, String problem) {
        if (problem == "withdraw")
            System.err.println("Can't withdraw money " + amount + " from user " + user.toString());
        else if (problem == "fund")
            System.err.println("Can't fund money " + amount + " to user " + user.toString());
    }
}
