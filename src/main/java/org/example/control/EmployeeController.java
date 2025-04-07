package org.example.control;

import org.example.model.*;
import org.nocrala.tools.texttablefmt.*;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    private EmployeeDao employeeDAO;

    public EmployeeController() {
        this.employeeDAO = new EmployeeDaoImpl();
    }

    public void addEmployee(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        if (!Validation.isValidId(String.valueOf(id))) {
            System.out.println("Invalid ID. Please enter a valid ID.");
            return;
        }
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (!Validation.isValidName(name)) {
            System.out.println("Invalid name. Please enter a valid name.");
            return;
        }
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        if (!Validation.isValidAge(String.valueOf(age))) {
            System.out.println("Invalid age. Please enter a valid age.");
        }
        scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        if (!Validation.isValidDepartment(department)) {
            System.out.println("Invalid department. Please enter a valid department.");
        }
        Employee student = new Employee(id, name, age, department);
        employeeDAO.addEmployee(student);
    }

    public void viewEmployees() {
        List<Employee> students = employeeDAO.getAllEmployees();
        if (students.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        Table t = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle alignCenter = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        t.setColumnWidth(0, 5, 10); // ID
        t.setColumnWidth(1, 25, 30); // Name
        t.setColumnWidth(2, 10, 15); // Age
        t.setColumnWidth(3, 20, 25); // Department
        
        t.addCell("ID", alignCenter);
        t.addCell("Name", alignCenter);
        t.addCell("Age", alignCenter);
        t.addCell("Department", alignCenter);

        for (Employee student : students) {
            t.addCell(String.valueOf(student.getId()), alignCenter);
            t.addCell(student.getName(), alignCenter);
            t.addCell(String.valueOf(student.getAge()), alignCenter);
            t.addCell(student.getDepartment(), alignCenter);
        }
        
        System.out.println(t.render());

        System.out.println("Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
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
        Employee employee = new Employee(id, name, age, department);
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        employeeDAO.deleteEmployee(id);
    }
}
