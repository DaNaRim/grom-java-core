package lesson30.homework;

import java.util.HashSet;

public class Controller {

    public HashSet<Employee> employeesByProject(String projectName) {
        HashSet<Employee> employees = null;

        for (Employee employee : EmployeeDAO.getEmployees()) {
            for (Project project : employee.getProjects().getProjects()) {
                if (projectName.equals(project.getName()))
                    employees.add(employee);
            }
        }
        return employees;
    }

    public HashSet<Project> projectsByEmployee(Employee employee) {
        return employee.getProjects().getProjects();
    }

    public employeesByDepartmentWithoutProject(DepartmentType departmentType) {

    }

    public employeesWithoutProject() {

    }

    public employeesByTeamLead(Employee lead) {

    }

    public teamLeadsByEmployee(Employee employee) {

    }

    public employeesByProjectEmployee(Employee employee) {

    }

    public projectsByCustomer(Customer customer) {

    }

    public employeesByCustomerProjects(Customer customer) {

    }
}