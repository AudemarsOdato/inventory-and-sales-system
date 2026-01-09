package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;
import models.Sale;

public class SalesHistory extends Database {

        static {
                createTable("sales", 
                        "cashier BIGINT REFERENCES users(id) NOT NULL, " +
                        "total_amount NUMERIC(10, 2) NOT NULL, " +
                        "cash_received NUMERIC(10, 2) NOT NULL, " +
                        "change NUMERIC(10, 2) NOT NULL, " +
                        "time_and_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL" // "use the database time as the source of truth." -chatgpt, 2026
                );
                createTable("sale_items", 
                        "sale_id INT NOT NULL REFERENCES sales(id)," +
                        "product_id INT NOT NULL REFERENCES products(id)," +
                        "quantity INT NOT NULL," +
                        "unit_price NUMERIC(10, 2) NOT NULL"
                );
        }

        // insert one
        public int insert(Sale sale) {
                int saleId = recordSale(sale);

                if (saleId == 0) {
                        return 0;
                }

                recordSaleItems(sale, saleId);

                return saleId;
        }

        private static int recordSale(Sale sale) {
                String statement = "INSERT INTO sales(cashier, total_amount, cash_received, change)" + 
                                "VALUES(?, ?, ?, ?) RETURNING id;";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        preparedStatement.setInt(1, sale.getCashierId());
                        preparedStatement.setDouble(2, sale.getTotalAmount());
                        preparedStatement.setDouble(3, sale.getCashReceived());
                        preparedStatement.setDouble(4, sale.getChangeAmount());
                        
                        ResultSet result = preparedStatement.executeQuery();

                        if (result.next()) {
                                return (int)result.getInt(1);
                        }
                }
                catch(SQLException e) {
                        e.printStackTrace();
                }
                return 0;
        }

        private boolean recordSaleItems(Sale sale, int saleId) {
                String statement = "INSERT INTO sale_items(sale_id, product_id, quantity, unit_price)" +
                                "VALUES(?, ?, ?, ?) RETURNING id;";

                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        for (Item item : sale.getItems()) {
                                preparedStatement.setInt(1, saleId);
                                preparedStatement.setInt(2, item.getProductId());
                                preparedStatement.setInt(3, item.getQuantity());
                                preparedStatement.setDouble(4, item.getUnitPrice());
                                preparedStatement.addBatch();
                        }
                        preparedStatement.executeBatch();
                        return true;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }

                return false;
        }

        // get all
        // @Override
        // public ArrayList<Sale> getAll() {
        //         // query all sales
        //         // query all sale items with the sales

        //         return null;
        // }


        // get one for confirmation

        
        // get sale items
        public ArrayList<Item> getSaleItems(int saleId) {
                String statement = "SELECT * FROM sale_items WHERE sale_id = " + saleId + ";";

                ArrayList<Item> items = new ArrayList<>();
                try (PreparedStatement preparedStatement = conn.prepareStatement(statement)) {
                        ResultSet result = preparedStatement.executeQuery();

                        while (result.next()) {
                                items.add(new Item(
                                        result.getInt("id"),
                                        result.getInt("sale_id"),
                                        result.getInt("product_id"),
                                        result.getInt("quantity"),
                                        result.getInt("unit_price")
                                ));
                        }
                }
                catch (SQLException e) {
                        e.printStackTrace();
                }
                return items;
        }
}