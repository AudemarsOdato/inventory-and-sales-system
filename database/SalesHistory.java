package database;

public class SalesHistory extends Database {

        static {
                // createTable("sales", "");
                // System.out.println("created products table");
        }

        @Override
        public void insert() {
                System.out.println("add sale");
        }
}