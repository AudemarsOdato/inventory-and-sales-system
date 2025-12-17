package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import models.Product;

public class Products extends Database {

        static {
                createTable("products", 
                        "name VARCHAR(255) NOT NULL, " + 
                        "image_path VARCHAR(255) NOT NULL, " +
                        "quantity INT NOT NULL, " +
                        "pricing DOUBLE PRECISION NOT NULL, " +
                        "total_amount DOUBLE PRECISION NOT NULL, " +
                        "last_stockup DATE NOT NULL"
                );
        }

        @Override
        public boolean insert(String name, String image_path, int quantity, double pricing) {
                String statement = "INSERT INTO products(name, image_path, quantity, pricing, total_amount, last_stockup) VALUES(?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, image_path);
                        preparedStatement.setInt(3, quantity);
                        preparedStatement.setDouble(4, pricing);
                        preparedStatement.setDouble(5, pricing * quantity);
                        preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));

                        int result = preparedStatement.executeUpdate();

                        return result == 1; // is result equal to 1
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public ArrayList<Product> getAll() {
                ArrayList<Product> products = new ArrayList<>();
                // create statement using conn
                // execute statement and store it into a resultset
                // take and put the datas into a array variable and display
                try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM products;")) {
                        ResultSet result = preparedStatement.executeQuery();

                        while (result.next()) {
                                int id = result.getInt("id");
                                String name = result.getString("name");
                                String image_path = result.getString("image_path");
                                int quantity = result.getInt("quantity");
                                double pricing = result.getDouble("pricing");
                                double totalAmount = result.getDouble("total_amount");
                                Date date = result.getDate("last_stockup");

                                // refactor: put the get methods directly, see Users.java
                                products.add(new Product(id, name, image_path, quantity, pricing, totalAmount, date));
                        }
                        return products;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }

                return null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Product getOne(int id) {

                try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM products WHERE id = " + id)) {
                        ResultSet result = preparedStatement.executeQuery();

                        while (result.next()) {
                                return new Product(
                                        result.getInt("id"),
                                        result.getString("name"),
                                        result.getString("image_path"),
                                        result.getInt("quantity"),
                                        result.getDouble("pricing"),
                                        result.getDouble("total_amount"),
                                        result.getDate("last_stockup")
                                );
                        }
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }

                return null;
        }

        @Override
        public boolean updateOne(int id, String name, String imagePath, int quantity, double pricing) {
                String statement = "UPDATE products " +
                                        "SET name = ?, " + 
                                        "image_path = ?, " + 
                                        "quantity = ?, " +
                                        "pricing = ?, " +
                                        "total_amount = ?, " +
                                        "last_stockup = ? " + 
                                        "WHERE id = " + id +";";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, imagePath);
                        preparedStatement.setInt(3, quantity);
                        preparedStatement.setDouble(4, pricing);
                        preparedStatement.setDouble(5, pricing * quantity);
                        preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));

                       int result = preparedStatement.executeUpdate();
                       
                       return result == 1;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }

                return false;
        }

        @Override
        public boolean deleteOne(int id) {
                
                try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM products WHERE id = " + id)) {
                        int result = preparedStatement.executeUpdate();

                        return result == 1;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }

                return false;
        }
}