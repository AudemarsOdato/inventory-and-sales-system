package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.User;

public class Users extends Database{

        static {
                createTable("users", 
                        "role VARCHAR(7) NOT NULL, " +
                        "username VARCHAR(255) NOT NULL, " +
                        "password TEXT NOT NULL, " +
                        "total_sales INT NOT NULL, " +
                        "UNIQUE(username)"
                );
        }

        @Override
        public boolean insert(String role, String username, String password) {
                String statement = "INSERT INTO users(role, username, password, total_sales) VALUES(?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, role.toLowerCase());
                        preparedStatement.setString(2, username.toLowerCase());
                        preparedStatement.setString(3, password);
                        preparedStatement.setInt(4, 0);

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
        public ArrayList<User> getAll() {
                ArrayList<User> users = new ArrayList<>();

                try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users;")) {
                        ResultSet result = preparedStatement.executeQuery();
                        
                        // int id, String username, String role, String password, int totalSales
                        while (result.next()) {
                                users.add(new User(
                                                result.getInt("id"), 
                                                result.getString("role"), 
                                                result.getString("username"), 
                                                result.getString("password"), 
                                                result.getInt("total_sales")
                                        )
                                );
                        }
                        return users;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return null;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public User getOne(int id) {
                try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE id = " + id + ";")) {
                        ResultSet result = preparedStatement.executeQuery();
                        while (result.next()) {
                                return new User(
                                        result.getInt("id"),
                                        result.getString("role"),
                                        result.getString("username"),
                                        result.getString("password"),
                                        result.getInt("total_sales")
                                );
                        }
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return null;
        }


        @Override
        public boolean updateOne(int id, String role, String username, String password, int totalSales) {
                String statement = "UPDATE users " +
                                        "SET role = ?, " + 
                                        "username = ?, " + 
                                        "password = ?, " +
                                        "total_sales = ? " + // increment
                                        "WHERE id = " + id + ";";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, role);
                        preparedStatement.setString(2, username);
                        preparedStatement.setString(3, role);
                        preparedStatement.setInt(4, totalSales); 

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
                try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id = " + id + ";")) {
                        int result = preparedStatement.executeUpdate();

                        return result == 1;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return false;
        }
}