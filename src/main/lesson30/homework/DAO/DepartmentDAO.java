package main.lesson30.homework.DAO;

import main.lesson30.homework.Model.Department;

import java.util.HashSet;

public class DepartmentDAO {

    private static HashSet<Department> departments;

    public static HashSet<Department> getDepartments() {
        return departments;
    }
}
