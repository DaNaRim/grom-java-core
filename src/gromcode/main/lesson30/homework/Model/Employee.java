package gromcode.main.lesson30.homework.Model;

import gromcode.main.lesson30.homework.DAO.ProjectDAO;

import java.util.Date;

public class Employee {
    private String firstName;
    private String lastName;
    private Date dateHired;
    private Position position;
    private Department department;
    private ProjectDAO projects;

    public Employee(String firstName, String lastName, Date dateHired, Position position, Department department, ProjectDAO projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateHired = dateHired;
        this.position = position;
        this.department = department;
        this.projects = projects;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public Position getPosition() {
        return position;
    }

    public Department getDepartment() {
        return department;
    }

    public ProjectDAO getProjects() {
        return projects;
    }
}