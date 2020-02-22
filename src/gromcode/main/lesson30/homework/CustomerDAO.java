package gromcode.main.lesson30.homework;

import java.util.HashSet;

public class CustomerDAO {
    private static HashSet<Customer> customers;

    public static HashSet<Customer> getCustomers() {
        return customers;
    }
}
