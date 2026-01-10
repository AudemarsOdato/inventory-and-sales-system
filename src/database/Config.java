package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Config extends Database{
        static {
                createTable("config", "store_title TEXT NOT NULL, UNIQUE(store_title)");
        }


        // store store name
        public int setStoreTitle(String storeTitle) {
                String statement = "INSERT INTO config(store_title) VALUES(?) RETURNING id;";
                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setString(1, storeTitle);
                        ResultSet result = preparedStatement.executeQuery();
                        if (result.next()) {
                                return (int)result.getInt("id");
                        }
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }
                return 0;
        }

        // getting store name
        public String getStoreTitle() {
                try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT store_title FROM config;")) {
                        ResultSet result = preparedStatement.executeQuery();
                        if (result.next()) {
                                return result.getString("store_title");
                        }
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }
                return null;
        }

        // updating or changing store name
        public String updateStoreTitle(String newStoreTitle) {
                try (PreparedStatement preparedStatement = conn.prepareStatement("UPDATE config SET store_title = ? RETURNING store_title;")) {
                        preparedStatement.setString(1, newStoreTitle);
                        ResultSet result = preparedStatement.executeQuery();
                        if (result.next()) {
                                return result.getString("store_title");
                        }
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }
                return null;
        }
}