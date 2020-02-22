package gromcode.main.lesson8.accounts;

public class CheckingAccount extends Account {
    int limitOFExpense;

    public CheckingAccount(String bankName, String ownerName, int moneyAmount, int limitOFExpense) {
        super(bankName, ownerName, moneyAmount);
        this.limitOFExpense = limitOFExpense;
    }

    void withdraw(int amount) {
        if (amount > limitOFExpense)
            return;
        moneyAmount -= amount;
    }


}
