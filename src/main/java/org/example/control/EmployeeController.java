package org.example.control;

import org.example.model.*;
import org.example.view.EmployeeView;
import org.nocrala.tools.texttablefmt.*;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    private EmployeeDao employeeDAO;
    private final EmployeeView view;

    // Color codes for text formatting
    private static final String GREEN = "\u001B[32m"; // Green
    private static final String BLUE = "\u001B[34m"; // Blue
    private static final String RESET = "\u001B[0m"; // Reset

    public EmployeeController() {
        this.view = new EmployeeView();
        this.employeeDAO = new EmployeeDaoImpl();
    }

    public void addEmployee(Scanner scanner) {
        view.showOrangeMessage("\nEnter ID: ");
        int id = scanner.nextInt();
        if (!Validation.isValidId(String.valueOf(id))) {
            view.showErrorMessage("Invalid ID. Please enter a valid ID.\n");
            return;
        }
        scanner.nextLine();
        view.showOrangeMessage("Enter Name: ");
        String name = scanner.nextLine();
        if (!Validation.isValidName(name)) {
            view.showErrorMessage("Invalid name. Please enter a valid name.\n");
            return;
        }
        view.showOrangeMessage("Enter Age: ");
        int age = scanner.nextInt();
        if (!Validation.isValidAge(String.valueOf(age))) {
            view.showErrorMessage("Invalid age. Please enter a valid age.\n");
            return;
        }
        scanner.nextLine();
        view.showOrangeMessage("Enter Department: ");
        String department = scanner.nextLine();
        if (!Validation.isValidDepartment(department)) {
            view.showErrorMessage("Invalid department. Please enter a valid department.\n");
        }
        Employee student = new Employee(id, name, age, department);
        employeeDAO.addEmployee(student);
    }

    public void viewEmployees() { 
        List<Employee> students = employeeDAO.getAllEmployees();
        if (students.isEmpty()) {
            view.showErrorMessage("No employees found.");
            return;
        }
        
        Table t = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle alignCenter = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        t.setColumnWidth(0, 5, 10); // ID
        t.setColumnWidth(1, 25, 30); // Name
        t.setColumnWidth(2, 10, 15); // Age
        t.setColumnWidth(3, 20, 25); // Department
        
        System.out.println("\n");
        t.addCell(BLUE + "ID" + RESET, alignCenter);
        t.addCell(BLUE + "Name" + RESET, alignCenter);
        t.addCell(BLUE + "Age" + RESET, alignCenter);
        t.addCell(BLUE + "Department" + RESET, alignCenter);

        for (Employee student : students) {
            t.addCell(GREEN + String.valueOf(student.getId()) + RESET, alignCenter);
            t.addCell(GREEN + student.getName() + RESET, alignCenter);
            t.addCell(GREEN + String.valueOf(student.getAge()) + RESET, alignCenter);
            t.addCell(GREEN + student.getDepartment() + RESET, alignCenter);
        }
        
        System.out.println(t.render());

        view.showInfoMessage("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void updateEmployee(Scanner scanner) {
        view.showOrangeMessage("\nEnter Student ID to update: ");
        int id = scanner.nextInt();
        if (!Validation.isValidId(String.valueOf(id))) {
            view.showErrorMessage("Invalid ID. Please enter a valid ID.\n");
        }
        scanner.nextLine();
        view.showOrangeMessage("Enter New Name: ");
        String name = scanner.nextLine();
        if (!Validation.isValidName(name)) {
            view.showErrorMessage("Invalid name. Please enter a valid name.\n");
        }
        view.showOrangeMessage("Enter New Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        if (!Validation.isValidAge(String.valueOf(age))) {
            view.showErrorMessage("Invalid age. Please enter a valid age.\n");
        }
        view.showOrangeMessage("Enter New Department: ");
        String department = scanner.nextLine();
        if (!Validation.isValidDepartment(department)) {
            view.showErrorMessage("Invalid department. Please enter a valid department.\n");
        }
        Employee employee = new Employee(id, name, age, department);
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(Scanner scanner) {
        view.showOrangeMessage("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        employeeDAO.deleteEmployee(id);
    }
}
