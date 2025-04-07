package org.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.view.EmployeeView;

public class EmployeeDaoImpl implements EmployeeDao {
    EmployeeView view = new EmployeeView();
    
    @Override
    public void addEmployee(Employee employee) {
        try (Connection conn = Connect.getConnection()) {
            String query = "INSERT INTO employees (id, name, age, department) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setInt(3, employee.getAge());
            stmt.setString(4, employee.getDepartment());
            stmt.executeUpdate();
            view.showSuccessMessage("Employee added successfully!\n");
        } catch (SQLException e) {
            view.showErrorMessage("Database error: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> students = new ArrayList<>();
        try (Connection conn = Connect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            while (rs.next()) {
                students.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("department")));
            }
        } catch (SQLException e) {
            view.showErrorMessage("Database error: " + e.getMessage());
        }
        return students;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Connection conn = Connect.getConnection()) {
            String query = "UPDATE employees SET name=?, age=?, department=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getAge());
            stmt.setString(3, employee.getDepartment());
            stmt.setInt(4, employee.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                view.showSuccessMessage("Employee updated successfully!\n");
            } else {
                view.showErrorMessage("Employee not found!\n");
            }
        } catch (SQLException e) {
            view.showErrorMessage("Database error: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection conn = Connect.getConnection()) {
            String query = "DELETE FROM employees WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                view.showSuccessMessage("Employee deleted successfully!\n");
            } else {
                view.showErrorMessage("Employee not found!\n");
            }
        } catch (SQLException e) {
            view.showErrorMessage("Database error: " + e.getMessage());
        }
    }

    @Override
    public int getID () {
        int id = 0;
        try (Connection conn = Connect.getConnection()) {
            String query = "SELECT id FROM employees ORDER BY id DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            view.showErrorMessage("Database error: " + e.getMessage());
        }
        return id;
    }
}
