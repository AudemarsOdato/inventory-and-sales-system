package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Sale;
import models.Sale.Item;

public class SalesHistory extends Database {

        static {
                createTable("sales", 
                        "cashier BIGINT REFERENCES users(id) NOT NULL, " +
                        "items JSONB[] NOT NULL, " + 
                        "total_amount DOUBLE PRECISION NOT NULL, " +
                        "cash_received DOUBLE PRECISION NOT NULL, " +
                        "change DOUBLE PRECISION NOT NULL, " +
                        "time_and_date TIMESTAMP NOT NULL" 
                );
        }

        // insert one
        public boolean insert(int cashierId, Item[] items, double totalAmount, double cashReceived, double change, Date timeAndDate) {
                String statement = "INSERT INTO sales(cashier, items, total_amount, cash_received, change, time_and_date) " + 
                                   "VALUE(?, ?::JSONB, ?, ?, ?, ?)";

                String jsonString = null;
                for (Item item : items) {
                        jsonString += "'" + item + "'" + ", ";
                        if (item == items[items.length]) {
                                
                        }
                }

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setInt(1, cashierId);
                        preparedStatement.setString(2, "{'items': {}}");
                }
                catch(SQLException error) {
                        error.printStackTrace();
                }

                return false;
        }


        // get all
        @Override
        public ArrayList<Sale> getAll() {
                return null;
        }


        // get one for confirmation

        
}