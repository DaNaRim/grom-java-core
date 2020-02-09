package lesson30.homework;

import java.util.HashSet;

public class EmployeeDAO {
    private static HashSet<Employee> employees;

    public static HashSet<Employee> getEmployees() {
        return employees;
    }
}
