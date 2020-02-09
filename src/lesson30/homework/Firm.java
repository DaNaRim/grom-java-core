package lesson30.homework;

import java.util.Date;

public class Firm {
    private Date dateFounded;
    private DepartmentDAO departments;
    private CustomerDAO customers;

    public Firm(Date dateFounded, DepartmentDAO departments, CustomerDAO customers) {
        this.dateFounded = dateFounded;
        this.departments = departments;
        this.customers = customers;
    }

    public Date getDateFounded() {
        return dateFounded;
    }

    public DepartmentDAO getDepartments() {
        return departments;
    }

    public CustomerDAO getCustomers() {
        return customers;
    }
}