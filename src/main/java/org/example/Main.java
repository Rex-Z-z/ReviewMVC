package org.example;

import java.util.InputMismatchException;
import org.example.view.EmployeeView;

public class Main {
    public static void main(String[] args) {
        EmployeeView employeeView = new EmployeeView();
        
        try {
            employeeView.start();
        } catch (InputMismatchException e) {
            System.out.println("An error occurred: " + e.getMessage());
            employeeView.start();
        }
    }
}
