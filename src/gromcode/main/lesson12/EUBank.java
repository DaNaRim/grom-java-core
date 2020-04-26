package gromcode.main.lesson12;

public class EUBank extends Bank {

    public EUBank(long id, String bankCountry, Currency currency, int numberOfEmployees, double avrSalaryOfEmployee, long rating, long totalCapital) {
        super(id, bankCountry, currency, numberOfEmployees, avrSalaryOfEmployee, rating, totalCapital);
    }

    @Override
    public int getLimitOfWithdrawal() {
        return getCurrency() == Currency.USD ? 2000 : 2200;
    }

    @Override
    public int getLimitOfFunding() {
        return getCurrency() == Currency.EUR ? 20000 : 10000;
    }

    @Override
    public double getMonthlyRate() {
        return getCurrency() == Currency.EUR ? 0.01 : 0;
    }

    @Override
    public double getCommission(int amount) {
        if (getCurrency() == Currency.USD)
            return amount <= 1000 ? 0.05 : 0.07;
        else
            return amount <= 1000 ? 0.02 : 0.04;
    }
}