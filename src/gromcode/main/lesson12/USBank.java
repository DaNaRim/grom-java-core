package gromcode.main.lesson12;

public class USBank extends Bank {

    public USBank(long id, String bankCountry, Currency currency, int numberOfEmployees, double avrSalaryOfEmployee, long rating, long totalCapital) {
        super(id, bankCountry, currency, numberOfEmployees, avrSalaryOfEmployee, rating, totalCapital);
    }

    @Override
    public int getLimitOfWithdrawal() {
        return getCurrency() == Currency.USD ? 1000 : 1200;
    }

    @Override
    public int getLimitOfFunding() {
        return getCurrency() == Currency.EUR ? 10000 : Integer.MAX_VALUE;
    }

    @Override
    public double getMonthlyRate() {
        return getCurrency() == Currency.EUR ? 0.02 : 0.01;
    }

    @Override
    public double getCommission(int amount) {
        if (getCurrency() == Currency.USD)
            return amount <= 1000 ? 0.05 : 0.07;
        else
            return amount <= 1000 ? 0.06 : 0.08;
    }
}
