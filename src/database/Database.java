package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database{
        public static Connection conn = null;

        private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
        private final static String USER = "postgres";
        private final static String PASSWORD = "trisha";

        static {
                connectDatabase();
        }

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

        protected static final boolean createTable(String tableName, String schema) {
                try {
                        Statement statement = conn.createStatement();
                        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + " ("+ 
                                                        "id BIGSERIAL NOT NULL PRIMARY KEY," +
                                                        schema +
                                                ");"
                        );
                        return true;
                }
                catch (SQLException error) {
                        error.printStackTrace();
                }
                return false;
        }

        protected boolean insert(String name, String image_path, int quantity, double pricing) {return false;}
        
        protected boolean insert(String role, String name, String password, int totalSales) {return false;}

        protected <T> ArrayList<T> getAll() {return null;} // dyanmic return value

        protected <T> T getOne(int id) {return null;}

        protected boolean updateOne(int id, String name, String imagePath, int quantity, double pricing) {return false;}

        protected boolean deleteOne(int id) {return false;}
}
