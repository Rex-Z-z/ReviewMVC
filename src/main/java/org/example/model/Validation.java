package org.example.model;

public class Validation {
    public static boolean isValidId(String id) {
        return id.matches("^[0-9]{1,10}$"); // Only numbers, max 10 digits
    }

    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]{1,50}$"); // Only letters & spaces, max 50 characters
    }

    public static boolean isValidAge(String age) {
        return age.matches("^(?:[1-9]|[1-9][0-9]|100)$"); // 1-100 range
    }

    public static boolean isValidDepartment(String department) {
        return department.matches("^[a-zA-Z\\s]{1,50}$"); // Only letters & spaces, max 50 characters
    }
}
