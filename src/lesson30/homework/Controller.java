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

    public HashSet<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        HashSet<Employee> employees = null;

        for (Employee employee : EmployeeDAO.getEmployees()) {
            if (employee.getDepartment().getType().equals(departmentType)) {
                if (employee.getProjects() == null)
                    employees.add(employee);
            }
        }
        return employees;
    }

    public HashSet<Employee> employeesWithoutProject() {
        HashSet<Employee> employees = null;

        for (Employee employee : EmployeeDAO.getEmployees()) {
            if (employee.getProjects() == null)
                employees.add(employee);
        }
        return employees;
    }

    public HashSet<Employee> employeesByTeamLead(Employee lead) {
        HashSet<Employee> employees = null;

        for (Project project : lead.getProjects().getProjects()) {
            employees.addAll(employeesByProject(project.getName()));
        }
        return employees;
    }

    public HashSet<Employee> teamLeadsByEmployee(Employee employee) {
        HashSet<Employee> employees = null;

        for (Project project : employee.getProjects().getProjects()) {
            for (Employee empl : employeesByProject(project.getName())) {
                if (empl.getPosition().equals(Position.TEAM_LEAD))
                    employees.add(empl);
            }
        }
        return employees;
    }

    public employeesByProjectEmployee(Employee employee) {

    }

    public projectsByCustomer(Customer customer) {

    }

    public employeesByCustomerProjects(Customer customer) {

    }
}
