package gromcode.main.lesson30.homework.Model;

import gromcode.main.lesson30.homework.DAO.EmployeeDAO;

public class Department {

    private DepartmentType type;
    private EmployeeDAO employees;

    public Department(DepartmentType type, EmployeeDAO employees) {
        this.type = type;
        this.employees = employees;
    }

    public DepartmentType getType() {
        return type;
    }

    public EmployeeDAO getEmployees() {
        return employees;
    }
}
