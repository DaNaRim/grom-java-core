package gromcode.main.lesson30.homework.DAO;

import gromcode.main.lesson30.homework.Model.Customer;
import gromcode.main.lesson30.homework.Model.DepartmentType;
import gromcode.main.lesson30.homework.Model.Employee;
import gromcode.main.lesson30.homework.Model.Project;

import java.util.HashSet;

public class EmployeeDAO {
    private static HashSet<Employee> employees;

    public static HashSet<Employee> getEmployees() {
        return employees;
    }

    public static HashSet<Employee> employeesByProject(String projectName) {
        HashSet<Employee> employees = new HashSet<>();

        for (Employee employee : getEmployees()) {
            for (Project project : ProjectDAO.getProjects()) {
                if (projectName.equals(project.getName()))
                    employees.add(employee);
            }
        }
        return employees;
    }

    public static HashSet<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        HashSet<Employee> employees = new HashSet<>();

        for (Employee employee : getEmployees()) {
            if (employee.getDepartment().getType().equals(departmentType) && employee.getProjects() == null) {
                employees.add(employee);
            }
        }
        return employees;
    }

    public static HashSet<Employee> employeesWithoutProject() {
        HashSet<Employee> employees = new HashSet<>();

        for (Employee employee : getEmployees()) {
            if (employee.getProjects() == null)
                employees.add(employee);
        }
        return employees;
    }

    public static HashSet<Employee> employeesByCustomerProjects(Customer customer) {
        HashSet<Employee> employees = new HashSet<>();

        for (Project project : ProjectDAO.projectsByCustomer(customer)) {
            if (project.getCustomer().equals(customer))
                employees.addAll(employeesByProject(project.getName()));
        }
        return employees;
    }

    public static HashSet<Employee> employeesByProjectEmployee(Employee employee) {
        HashSet<Employee> employees = new HashSet<>();

        for (Project project : employee.getProjects().getProjects()) {
            employees.addAll(employeesByProject(project.getName()));
        }
        return employees;
    }

}
