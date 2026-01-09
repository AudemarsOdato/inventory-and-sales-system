package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import models.Item;
import models.Product;

public class Products extends Database {

        static {
                createTable("products", 
                        "name VARCHAR(255) NOT NULL, " + 
                        "image_path VARCHAR(255) NOT NULL, " +
                        "quantity INT NOT NULL, " +
                        "pricing DOUBLE PRECISION NOT NULL, " +
                        "last_stockup DATE NOT NULL DEFAULT CURRENT_DATE"
                );
        }

        public int insert(String name, String image_path, int quantity, double pricing) {
                String statement = "INSERT INTO products(name, image_path, quantity, pricing, total_amount) VALUES(?, ?, ?, ?, ?) RETURNING id;";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, image_path);
                        preparedStatement.setInt(3, quantity);
                        preparedStatement.setDouble(4, pricing);
                        preparedStatement.setDouble(5, pricing * quantity);
                        // Date.valueOf(LocalDate.now())

                        ResultSet result = preparedStatement.executeQuery();

                        if (result.next()) {
                                return (int)result.getInt(1);
                        }
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return 0;
        }

        public ArrayList<Product> getAll() {
                ArrayList<Product> products = new ArrayList<>();
                
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
                                products.add(new Product(id, name, image_path, quantity, pricing, date));
                        }
                        return products;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return null;
        }

        public Product getOne(int id) {
                try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM products WHERE id = " + id + ";")) {
                        ResultSet result = preparedStatement.executeQuery();

                        while (result.next()) {
                                return new Product(
                                        result.getInt("id"),
                                        result.getString("name"),
                                        result.getString("image_path"),
                                        result.getInt("quantity"),
                                        result.getDouble("pricing"),
                                        result.getDate("last_stockup")
                                );
                        }
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return null;
        }

        public boolean updateOne(int id, String name, String imagePath, double pricing) {
                String statement = "UPDATE products " +
                                        "SET name = ?, " + 
                                        "image_path = ?, " + 
                                        "pricing = ?, " +
                                        "last_stockup = ? " + 
                                        "WHERE id = " + id +";";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, imagePath);
                        preparedStatement.setDouble(4, pricing);
                        preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));

                       int result = preparedStatement.executeUpdate();
                       
                       return result == 1;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return false;
        }

        public int[] updateInventory(ArrayList<Item> items) {
                String statement = "UPDATE products SET quantity = quantity - ? WHERE id = ? RETURNING quantity;";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        for (Item item : items) {
                                preparedStatement.setInt(1, item.getQuantity());
                                preparedStatement.setInt(2, item.getProductId());
                                preparedStatement.addBatch();
                        }

                        int[] result = preparedStatement.executeBatch();
                        return result;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }
                return null;
        }

        public int updateRestocks(Item item) {
                String statement = "UPDATE products SET quantity = quantity + ? WHERE id = ? RETURNING quantity;";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setInt(1, item.getQuantity());
                        preparedStatement.setInt(2, item.getId());

                        ResultSet result = preparedStatement.executeQuery();
                        if (result.next()) {
                                return (int)result.getInt(1);
                        }
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }
                return 0;
        }

        public boolean deleteOne(int id) {
                try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM products WHERE id = " + id + ";")) {
                        int result = preparedStatement.executeUpdate();
                        return result == 1;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return false;
        }
}