package com.jpd.shop.server.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jpd.shop.common_files.EmployeeLoginInfo;
import com.jpd.shop.common_files.ProductData;

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

            if (!result.next()) {
                employeeLoginInfo = null;
                return null;
            }

            int id = result.getInt("id_pk");
            String type = result.getString("type");
            String username = result.getString("username");
            String password = result.getString("password");

            if (employeeLoginInfo.username().equals(username)
                    && employeeLoginInfo.password().equals(password)) {

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

    static ProductData[] getProducts() {
        if (databaseConnection == null) {
            databaseConnection = getConnection();
        }

        ProductData[] products = null;
        String query = "SELECT * FROM products ORDER BY stock DESC";

        try (PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            ArrayList<ProductData> productDataList = new ArrayList<>();

            String name;
            int price;
            int stock;
            String category;
            byte[] image;
            int id;

            while (resultSet.next()) {
                name = resultSet.getString("name");
                price = (int) (resultSet.getFloat("price") * 100);
                stock = resultSet.getInt("stock");
                category = resultSet.getString("category");
                image = resultSet.getBytes("image");
                id = resultSet.getInt("id_pk");

                productDataList.add(
                        new ProductData(name, price, stock, category, image, id));
            }

            products = new ProductData[productDataList.size()];
            products = productDataList.toArray(products);

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            return products;
        }
    }

    static boolean addNewProduct(ProductData newProduct) {
        if (databaseConnection == null) {
            databaseConnection = getConnection();
        }

        boolean isSuccessful = false;
        String query = "INSERT INTO products"
                + "(name, price, stock, category, image)"
                + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(query)) {

            preparedStatement.setString(1, newProduct.name());
            preparedStatement.setFloat(2, newProduct.price() / 100.f);
            preparedStatement.setInt(3, newProduct.stock());
            preparedStatement.setString(4, newProduct.category());
            preparedStatement.setBytes(5, newProduct.image());

            isSuccessful = (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        } finally {
            return isSuccessful;
        }
    }

    private static Connection getConnection() {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/ikaw_bahala";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Connection connection = null;

        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
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
