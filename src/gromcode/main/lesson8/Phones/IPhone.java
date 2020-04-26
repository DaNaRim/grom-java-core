package gromcode.main.lesson8.Phones;

public class IPhone extends Phone {
    boolean fingerprint;

    public IPhone(int price, double weight, String countryProduced, boolean fingerprint) {
        super(price, weight, countryProduced);
        System.out.println("IPhone constructor was invoked...");
        this.fingerprint = fingerprint;
    }

    void deleteIPhoneFromDb() {
        System.out.println("deleteIPhoneFromDb invoked...");
    }
}