package lesson30.task1;

public class ChinaBank extends Bank {

    public ChinaBank(long id, String bankCountry, Currency currency, int numberOfEmployees, double avrSalaryOfEmployee, long rating, long totalCapital) {
        super(id, bankCountry, currency, numberOfEmployees, avrSalaryOfEmployee, rating, totalCapital);
    }

    @Override
    public int getLimitOfWithdrawal() {
        return getCurrency() == Currency.USD ? 100 : 150;
    }

    @Override
    public int getLimitOfFunding() {
        return getCurrency() == Currency.EUR ? 5000 : 10000;
    }

    @Override
    public double getMonthlyRate() {
        return getCurrency() == Currency.EUR ? 0 : 0.01;
    }

    @Override
    public double getCommission(int amount) {
        if (getCurrency() == Currency.USD)
            return amount <= 1000 ? 0.03 : 0.1;
        else
            return amount <= 1000 ? 0.1 : 0.11;
    }
}
