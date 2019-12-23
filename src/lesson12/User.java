package lesson12;

public class User {
    private long id;
    private String name;
    private double balance;
    private int monthOfEmployee;
    private String companyName;
    private int salary;
    private Bank bank;

    public User(long id, String name, double balance, int monthOfEmployee, String companyName, int salary, Bank bank) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.monthOfEmployee = monthOfEmployee;
        this.companyName = companyName;
        this.salary = salary;
        this.bank = bank;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMonthOfEmployee(int monthOfEmployee) {
        this.monthOfEmployee = monthOfEmployee;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getMonthOfEmployee() {
        return monthOfEmployee;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getSalary() {
        return salary;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", monthOfEmployee=" + monthOfEmployee +
                ", companyName='" + companyName + '\'' +
                ", salary=" + salary +
                ", bank=" + bank +
                '}';
    }
}