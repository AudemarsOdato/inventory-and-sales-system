package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Products extends Database {

        static {
                createTable("products", "name VARCHAR(50) NOT NULL, " + 
                                        "image_path VARCHAR(200) NOT NULL, " +
                                        "quantity INT NOT NULL, " +
                                        "pricing DOUBLE PRECISION NOT NULL, " +
                                        "total_amount DOUBLE PRECISION NOT NULL, " +
                                        "last_stockup DATE NOT NULL"
                );
        }

        // adding a new product
        @Override
        public void insert(String name, String image_path, int quantity, double pricing) {
                LocalDate stockupDate = LocalDate.now();

                // prepare statement
                // create statement using conn
                // add the values using set method on the prepared statement
                // execute statement
                // check if successful by rows affected
                String statement = "INSERT INTO products(name, image_path, quantity, pricing, total_amount, last_stockup) VALUES(?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, image_path);
                        preparedStatement.setInt(3, quantity);
                        preparedStatement.setDouble(4, pricing);
                        preparedStatement.setDouble(5, pricing * quantity);
                        preparedStatement.setDate(6, Date.valueOf(stockupDate));

                        int result = preparedStatement.executeUpdate();
                        
                        if (result == 1) {
                                System.out.println("added row, " + result + " to " + " products");

                        }
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
        }

        // getting all the products
        @Override
        public void getAll() {
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

                                System.out.println(id + " " + name + " " + image_path + " " + quantity + " " + pricing + " " + totalAmount + " " + date);
                        }
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
        }

        // updating a product details
        // deleting a product
}