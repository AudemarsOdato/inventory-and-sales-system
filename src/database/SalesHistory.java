package database;

import java.util.ArrayList;
import models.Sale.Item;

public class SalesHistory extends Database {

        static {
                createTable("sales", 
                        "cashier BIGINT REFERENCES users(id) NOT NULL, " +
                        "total_amount DOUBLE PRECISION NOT NULL, " +
                        "cash_received DOUBLE PRECISION NOT NULL, " +
                        "change DOUBLE PRECISION NOT NULL, " +
                        "time_and_date TIMESTAMP now()" // "use the database time as the source of truth." -chatgpt, 2026
                );
                createTable("sale_items", 
                        "sale_id INT NOT NULL REFERENCES sales(id)," +
                        "product_id INT NOT NULL REFERENCES products(id)," +
                        "quantity INT NOT NULL," +
                        "unit_price NUMERIC(10, 2) NOT NULL"
                );
        }

        // insert one
        public int insert(int cashierId, ArrayList<Item> items, double totalAmount, double cashReceived, double change) {
                return 1;
        }


        // get all
        // @Override
        // public ArrayList<Sale> getAll() {
        //         return null;
        //}


        // get one for confirmation

        
}