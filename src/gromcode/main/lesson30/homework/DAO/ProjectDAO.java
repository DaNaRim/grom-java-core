package gromcode.main.lesson30.homework.DAO;

import gromcode.main.lesson30.homework.Model.Customer;
import gromcode.main.lesson30.homework.Model.Employee;
import gromcode.main.lesson30.homework.Model.Position;
import gromcode.main.lesson30.homework.Model.Project;

import java.util.HashSet;

public class ProjectDAO {
    private static HashSet<Project> projects;

    public static HashSet<Project> getProjects() {
        return projects;
    }

    public static HashSet<Employee> employeesByTeamLead(Employee lead) {
        HashSet<Employee> employees = new HashSet<>();

        for (Project project : lead.getProjects().getProjects()) {
            employees.addAll(EmployeeDAO.employeesByProject(project.getName()));
        }
        return employees;
    }

    public static HashSet<Employee> teamLeadsByEmployee(Employee employee) {
        HashSet<Employee> employees = new HashSet<>();

        for (Project project : employee.getProjects().getProjects()) {
            for (Employee empl : EmployeeDAO.employeesByProject(project.getName())) {
                if (empl.getPosition().equals(Position.TEAM_LEAD))
                    employees.add(empl);
            }
        }
        return employees;
    }

    public static HashSet<Project> projectsByCustomer(Customer customer) {
        HashSet<Project> projects = new HashSet<>();

        for (Project project : getProjects()) {
            if (project.getCustomer().equals(customer))
                projects.add(project);
        }
        return projects;
    }

}
