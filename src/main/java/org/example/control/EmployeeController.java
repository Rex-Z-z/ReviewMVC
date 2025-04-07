package org.example.control;

import org.example.model.*;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    private StudentDao studentDAO;

    public EmployeeController() {
        this.studentDAO = new EmployeeDaoImpl();
    }

    public void addEmployee(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        Employee student = new Employee(id, name, age, department);
        studentDAO.addEmployee(student);
    }

    public void viewEmployees() {
        List<Employee> students = studentDAO.getAllEmployees();
        if (students.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("\nID | Name | Age | Department");
        for (Employee student : students) {
            System.out.printf("%d | %s | %d | %s\n", student.getId(), student.getName(), student.getAge(), student.getDepartment());
        }
    }

    public void updateEmployee(Scanner scanner) {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Department: ");
        String department = scanner.nextLine();
        Employee student = new Employee(id, name, age, department);
        studentDAO.updateEmployee(student);
    }

    public void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        studentDAO.deleteEmployee(id);
    }
}
