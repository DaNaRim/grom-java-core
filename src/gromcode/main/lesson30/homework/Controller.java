package gromcode.main.lesson30.homework;

import java.util.HashSet;

public class Controller {

    public static HashSet<Employee> employeesByProject(String projectName) {
        HashSet<Employee> employees = null;

        for (Employee employee : EmployeeDAO.getEmployees()) {
            for (Project project : employee.getProjects().getProjects()) {
                if (projectName.equals(project.getName()))
                    employees.add(employee);
            }
        }
        return employees;
    }

    public static HashSet<Project> projectsByEmployee(Employee employee) {
        return employee.getProjects().getProjects();
    }

    public static HashSet<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {
        HashSet<Employee> employees = null;

        for (Employee employee : EmployeeDAO.getEmployees()) {
            if (employee.getDepartment().getType().equals(departmentType)) {
                if (employee.getProjects() == null)
                    employees.add(employee);
            }
        }
        return employees;
    }

    public static HashSet<Employee> employeesWithoutProject() {
        HashSet<Employee> employees = null;

        for (Employee employee : EmployeeDAO.getEmployees()) {
            if (employee.getProjects() == null)
                employees.add(employee);
        }
        return employees;
    }

    public static HashSet<Employee> employeesByTeamLead(Employee lead) {
        HashSet<Employee> employees = null;

        for (Project project : lead.getProjects().getProjects()) {
            employees.addAll(employeesByProject(project.getName()));
        }
        return employees;
    }

    public static HashSet<Employee> teamLeadsByEmployee(Employee employee) {
        HashSet<Employee> employees = null;

        for (Project project : employee.getProjects().getProjects()) {
            for (Employee empl : employeesByProject(project.getName())) {
                if (empl.getPosition().equals(Position.TEAM_LEAD))
                    employees.add(empl);
            }
        }
        return employees;
    }

    public static HashSet<Employee> employeesByProjectEmployee(Employee employee) {
        HashSet<Employee> employees = null;

        for (Project project : employee.getProjects().getProjects()) {
            employees.addAll(employeesByProject(project.getName()));
        }
        return employees;
    }

    public static HashSet<Project> projectsByCustomer(Customer customer) {
        HashSet<Project> projects = null;

        for (Project project : ProjectDAO.getProjects()) {
            if (project.getCustomer().equals(customer))
                projects.add(project);
        }
        return projects;
    }

    public static HashSet<Employee> employeesByCustomerProjects(Customer customer) {
        HashSet<Employee> employees = null;

        for (Project project : projectsByCustomer(customer)) {
            if (project.getCustomer().equals(customer))
                employees.addAll(employeesByProject(project.getName()));
        }
        return employees;
    }
}