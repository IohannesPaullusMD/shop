package com.jpd.shop.server.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.data_types.EmployeeLoginInfo;
import com.jpd.shop.common_files.data_types.ProductData;

@SuppressWarnings("finally")
public class DatabaseQueries {
    private static Connection databaseConnection;

    static EmployeeLoginInfo checkIfEmployeeLoginInfoExists(
            EmployeeLoginInfo employeeLoginInfo) {

        String query = "SELECT * FROM employees WHERE username = ? && password = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {

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

    static ProductData[] getProducts(int productsCategory) {

        ProductData[] products = null;
        String query = "SELECT * FROM products WHERE category = ?"
                + " ORDER BY stock DESC";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setInt(1, productsCategory - 10);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<ProductData> productDataList = new ArrayList<>();

            String name;
            int price;
            int stock;
            int category;
            byte[] image;
            int id;

            while (resultSet.next()) {
                name = resultSet.getString("name");
                price = (int) (resultSet.getFloat("price") * 100);
                stock = resultSet.getInt("stock");
                category = resultSet.getInt("category");
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

    static void addNewProduct(ProductData newProduct) {

        String query = "INSERT INTO products"
                + "(name, price, stock, category, image)"
                + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            preparedStatement.setString(1, newProduct.name());
            preparedStatement.setFloat(2, newProduct.price() / 100.f);
            preparedStatement.setInt(3, newProduct.stock());
            preparedStatement.setInt(4, newProduct.category());
            preparedStatement.setBytes(5, newProduct.image());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    static void updateProductDetails(ProductData productData) {

        String query = "UPDATE products "
                + "SET name = ?, price = ?, stock = ?, "
                + "category = ?, image = ? WHERE id_pk = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, productData.name());
            preparedStatement.setFloat(2, productData.price() / 100.f);
            preparedStatement.setInt(3, productData.stock());
            preparedStatement.setInt(4, productData.category());
            preparedStatement.setBytes(5, productData.image());
            preparedStatement.setInt(6, productData.id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    static void deleteProduct(int id_pk) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "DELETE FROM products WHERE id_pk = " + id_pk)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    private static Connection getConnection() {
        if (databaseConnection == null) {
            connectToDatabase();
        }

        return databaseConnection;
    }

    private static void connectToDatabase() {

        final String DATABASE_URL = "jdbc:mysql://localhost:3306/ikaw_bahala";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
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
