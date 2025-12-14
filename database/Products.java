package database;

public class Products {
        public Products() {
                new Database().createTable("products", "");
                new Database().createTable("users", "username VARCHAR(50), role VARCHAR(10)");
        }

        // adding a new product
        // getting all the products
        // updating a product details
        // deleting a product
}