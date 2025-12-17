package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import models.User;

public class Users extends Database{

        static {
                createTable("users", 
                        "role VARCHAR(7) NOT NULL, " +
                        "username VARCHAR(255) NOT NULL, " +
                        "password TEXT NOT NULL, " +
                        "total_sales INT NOT NULL"
                );
        }

        // create new user
        @Override
        public boolean insert(String role, String name, String password, int totalSales) {
                String statement = "INSERT INTO users(role, username, password, total_sales) VALUES(?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, role);
                        preparedStatement.setString(2, name);
                        preparedStatement.setString(3, password);
                        preparedStatement.setInt(4, totalSales);

                        int result = preparedStatement.executeUpdate();

                        return result == 1; // is result equal to 1
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return false;
        }

        // get all user


        // get one user


        // update one user


        // delete one user
}