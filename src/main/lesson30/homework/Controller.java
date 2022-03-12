package main.lesson30.homework;

import main.lesson30.homework.DAO.EmployeeDAO;
import main.lesson30.homework.DAO.ProjectDAO;
import main.lesson30.homework.Model.Customer;
import main.lesson30.homework.Model.DepartmentType;
import main.lesson30.homework.Model.Employee;
import main.lesson30.homework.Model.Project;

import java.util.HashSet;

public class Controller {

    public static HashSet<Employee> employeesByProject(String projectName) {
        return EmployeeDAO.employeesByProject(projectName);
    }

    public static HashSet<Project> projectsByEmployee(Employee employee) {
        return ProjectDAO.getProjects();
    }

    public static HashSet<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        return EmployeeDAO.employeesByDepartmentWithoutProject(departmentType);
    }

    public static HashSet<Employee> employeesWithoutProject() {
        return EmployeeDAO.employeesWithoutProject();
    }

    public static HashSet<Employee> employeesByTeamLead(Employee lead) {
        return ProjectDAO.employeesByTeamLead(lead);
    }

    public static HashSet<Employee> teamLeadsByEmployee(Employee employee) {
        return ProjectDAO.teamLeadsByEmployee(employee);
    }

    public static HashSet<Employee> employeesByProjectEmployee(Employee employee) {
        return EmployeeDAO.employeesByProjectEmployee(employee);
    }

    public static HashSet<Project> projectsByCustomer(Customer customer) {
        return ProjectDAO.projectsByCustomer(customer);
    }

    public static HashSet<Employee> employeesByCustomerProjects(Customer customer) {
        return EmployeeDAO.employeesByCustomerProjects(customer);
    }
}
