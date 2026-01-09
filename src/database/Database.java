package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database{
        // https://www.google.com/search?q=make+instance+of+object+static&oq=make+instance+of+object+static&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIHCAEQIRigATIHCAIQIRifBTIHCAMQIRifBTIHCAQQIRifBdIBCTEwMTgyajBqN6gCALACAA&sourceid=chrome&ie=UTF-8
        // https://www.google.com/search?q=singleton+class+in+java&oq=single&gs_lcrp=EgZjaHJvbWUqDAgFEAAYQxiABBiKBTIRCAAQRRg5GEYY-QEYsQMYgAQyDQgBEAAYkQIYgAQYigUyDQgCEAAYkQIYgAQYigUyEggDEAAYQxiDARixAxiABBiKBTIMCAQQABhDGIAEGIoFMgwIBRAAGEMYgAQYigUyBwgGEAAYgAQyCggHEAAYsQMYgAQyDQgIEC4YgwEYsQMYgAQyBwgJEAAYjwLSAQg3OTk5ajBqN6gCALACAA&sourceid=chrome&ie=UTF-8
        protected  static Connection conn = null;

        // -classpath ".\lib\postgresql-42.2.29.jre7.jar"
        public static void connect(String url, String user, String password) {
                try {
                        conn = DriverManager.getConnection(url, user, password);
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

        protected <T> ArrayList<T> getAll() {return null;} // dyanmic return value

        protected <T> T getOne(int id) {return null;}

        protected boolean updateOne(int id, String name, String imagePath, int quantity, double pricing) {return false;}

        protected boolean updateOne(int id, String role, String username, String password, int totalSales) {return false;}

        protected boolean deleteOne(int id) {return false;}
}
