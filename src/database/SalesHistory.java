package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}