package com.jpd.shop.server.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jpd.shop.common_files.EmployeeLoginInfo;

@SuppressWarnings("finally")
public class DatabaseQueries {
    private static Connection databaseConnection;

    static EmployeeLoginInfo checkIfEmployeeLoginInfoExists(
            EmployeeLoginInfo employeeLoginInfo) {

        if (databaseConnection == null) {
            databaseConnection = getConnection();
        }

        String query = "SELECT * FROM employees WHERE username = ? && password = ?";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {

            statement.setString(1, employeeLoginInfo.username());
            statement.setString(2, employeeLoginInfo.password());
            ResultSet result = statement.executeQuery();
            result.next();

            int id = result.getInt("id_pk");
            String type = result.getString("type");
            String username = result.getString("username");
            String password = result.getString("password");

            if (employeeLoginInfo.username().equals(username)) {
                employeeLoginInfo = new EmployeeLoginInfo(id, type, username, password);
            } else {
                employeeLoginInfo = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            return employeeLoginInfo;
        }
    }

    private static Connection getConnection() {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/ikaw_bahala";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Connection connection = null;

        try {
            // Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }

    }

    private static void closeConnection() {
        try {
            if (databaseConnection != null) {
                databaseConnection.close();
                databaseConnection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DatabaseQueries() {
    }
}
