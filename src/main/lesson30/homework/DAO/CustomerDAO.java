package main.lesson30.homework.DAO;

import main.lesson30.homework.Model.Customer;

import java.util.HashSet;

public class CustomerDAO {

    private static HashSet<Customer> customers;

    public static HashSet<Customer> getCustomers() {
        return customers;
    }
}
