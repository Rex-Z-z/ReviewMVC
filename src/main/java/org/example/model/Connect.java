package org.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Class
 * <p>
 * This class is responsible for establishing a connection to the PostgreSQL database.
 * <p>
 * Make sure to verify the database connection details (URL, USER, PASSWORD) before running the project.
 * </p>
 *
 * <h3>SQL Query to Create the 'students' Table</h3>
 * <pre>
 * CREATE TABLE students (
 *     id INT AUTO_INCREMENT PRIMARY KEY,
 *     name VARCHAR(50) NOT NULL,
 *     age INT CHECK (age > 0 AND age <= 100),
 *     department VARCHAR(50) NOT NULL
 * );
 * </pre>
 *
 * @author Peng Chhaitith
 * @version 1.0
 */
public class Connect {
    // Database credentials - Ensure these are correct before running the application
    private static final String URL = "jdbc:postgresql://localhost:5432/testing"; // Update host/port if necessary
    private static final String USER = "postgres"; // Change according to your PostgreSQL user
    private static final String PASSWORD = "Reach124404"; // Change according to your PostgreSQL password

    /**
     * Establishes and returns a database connection.
     *
     * @return Connection object if successful
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
