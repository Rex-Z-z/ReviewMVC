package org.example.model;

import java.util.List;

public interface StudentDao {
    void addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
}