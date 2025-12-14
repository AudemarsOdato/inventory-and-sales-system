package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
        private static Connection conn = null;
        private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
        private final static String USER = "postgres";
        private final static String PASSWORD = "trisha";

        // classpath -classpath ".\lib\postgresql-42.2.29.jre7.jar"
        private static void connectDatabase() {
                try {
                        conn = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("connected to db");
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
        }

        public Connection getConnection() {
                return conn;
        }

        // create table with schemas as parameter
        public boolean createTable(String table_name, String schema) {
                // create statement using connection
                // execute using the statement
                try {
                        Statement statement = conn.createStatement();
                        statement.execute("CREATE TABLE IF NOT EXISTS " + table_name + "("+ 
                                                "id BIGSERIAL NOT NULL PRIMARY KEY," +
                                                schema +");"
                        );
                        return true;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return false;
        }

        public Database() {
                connectDatabase();
        }
}
