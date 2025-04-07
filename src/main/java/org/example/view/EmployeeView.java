package org.example.view;

import org.example.control.EmployeeController;
import org.nocrala.tools.texttablefmt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeView {
    // Color codes for text formatting
    private static final String RED = "\u001B[31m"; // Red
    private static final String GREEN = "\u001B[32m"; // Green
    private static final String YELLOW = "\u001B[33m"; // Yellow
    private static final String BLUE = "\u001B[34m"; // Blue
    private static final String LIGHT_ORANGE = "\u001B[38;5;214m"; // Orange
    private static final String RESET = "\u001B[0m"; // Reset
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        EmployeeController controller = new EmployeeController();

        while (true) {
            Table t = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            CellStyle alignCenter = new CellStyle(CellStyle.HorizontalAlign.CENTER);
            t.setColumnWidth(0, 5, 10);
            t.setColumnWidth(1, 25, 30);

            t.addCell(BLUE + "Employee Management System" + RESET, alignCenter, 2);
            t.addCell("1", alignCenter);
            t.addCell(" Add Employee");
            t.addCell("2", alignCenter);
            t.addCell(" View Employees");
            t.addCell("3", alignCenter);
            t.addCell(" Update Employee");
            t.addCell("4", alignCenter);
            t.addCell(" Delete Employee");
            t.addCell("5", alignCenter);
            t.addCell(" Exit");
            System.out.println(t.render());

            try {
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> controller.addEmployee(scanner);
                    case 2 -> controller.viewEmployees();
                    case 3 -> controller.updateEmployee(scanner);
                    case 4 -> controller.deleteEmployee(scanner);
                    case 5 -> {
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Try again.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.nextLine();
            }
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showSuccessMessage(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public void showErrorMessage(String message) {
        System.out.print(RED + message + RESET);
    }

    public void showWarningMessage(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    public void showInfoMessage(String message) {
        System.out.println(BLUE + message + RESET);
    }

    public void showOrangeMessage(String message) {
        System.out.print(LIGHT_ORANGE + message + RESET);
    }
}

